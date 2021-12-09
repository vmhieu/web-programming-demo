import React, { useEffect, useState } from 'react';
import { Table, Tag, Space, Tabs, Modal, Button, Alert,notification } from 'antd';
import { EditOutlined, DeleteOutlined, PlusOutlined, ReloadOutlined,TwitterOutlined ,CheckOutlined} from '@ant-design/icons';
import ModalForm from './Form/FormRooms/ModalForm';
import { roomAPI, serviceAPI, visiterAPI } from '../fake-api/student-API';
import { getAllRooms } from '../service/account';
import axios from 'axios';


function Rooms(props) {


    const [row, setRow] = useState(false);
    const [data, setData] = useState([])
    const [option, setOption] = useState(null)
    const [modalForm, setModalForm] = useState(false)
    const _requestData = async (param ={}) => {
        const data = await getAllRooms()
        console.log("datassssssssss", data.data)
        const dataConvert = data.data.map(i => {
            return i
        })
        setData(dataConvert)
    }


    useEffect(() => {
        _requestData()
    } ,[])

    const openNotification = () => {
        notification.open({
          message: 'Xóa thành công',
          description:
          <TwitterOutlined style={{color : '#93b874'}}/> ,
          icon: <CheckOutlined style={{ color: '#108ee9' }} />,
        });
      };
    const _handleRow = (val) => {
        setRow(val);
    }
    const handleSelect = async (data, type) => {
        if (type == "edit") {
            setRow(false);
            setModalForm({
                data,
                type
            })
        }
        if(type == "add"){
            setRow(false)
            setModalForm({
                data,
                type
            })
        }
        if (type == "del") {
            // console.log({data , type})
            setRow(false)
            const r = window.confirm("Bạn có muốn xóa item này không")
            if(r == true) {
               try {
                await axios.delete(`http://localhost:8080/api/room/${data.id}`)
                openNotification()
                _requestData();
               } catch (error) {
                   console.log("err" ,error)
               }
            }
        }
    }

    return (
        <div>
            <div>
                <Button onClick={() => {
                    handleSelect("" , "add")
                }} style={{ margin: "0  0  15px 30px", borderRadius: "15px" }} icon={<PlusOutlined />}>Thêm mới</Button>
                <Button onClick={() => {
                    setData(false)
                    _requestData()
                }} style={{ margin: "0  0  15px 5px", borderRadius: "15px" }} icon={<ReloadOutlined />}>Làm mới</Button>
            </div>
            <Table
                columns={columns}
                dataSource={data}
                onRow={(r) => ({
                    onClick: () => {
                        _handleRow(r)
                    },
                })}
            />
            <div style={{ display: "flex", justifyContent: 'center', alignContent: 'center' }}>
                <Modal
                    title="Chỉnh sửa"
                    visible={row}
                    footer={null}
                    // style={{width: 250}}
                    minWidth={400}
                    onCancel={() => {
                        setRow(false)
                        setOption("");
                    }}
                    bodyStyle={{ borderRadius: '15px' }}
                >
                    <div style={{ display: "flex", justifyContent: "space-around" }}>
                        <Button
                            style={{ alignItems: "center", width: '120px', height: '60px', borderRadius: '10px', backgroundColor: "DodgerBlue" }}
                            onClick={() => handleSelect(row, "edit")}
                        >
                            <EditOutlined />
                            Chỉnh sửa
                        </Button>
                        <Button
                            style={{ display: "flex", alignItems: "center", width: '120px', height: '60px', borderRadius: '10px', backgroundColor: "#616161" }}
                            onClick={() => handleSelect(row, "del")}
                            type="danger"
                        >
                            <DeleteOutlined />
                            Xóa
                        </Button>
                    </div>
                </Modal>
            </div>
            <ModalForm
                visible={modalForm}
                onCancel={() => {
                    console.log("asdada")
                    setModalForm(false)
                }}
            />

        </div>
    );
}

const columns = [
    {
        title : 'id',
        dataIndex : 'id',
        key : 'id'
    },
    {
        title : "Kiểu phòng" , 
        dataIndex : 'type',
        key : 'type'
    },
    {
        title : 'Số người tối đa',
        dataIndex : 'maximum',
        key : 'maximum'
    },
    {
        title : "Tổng số người",
        dataIndex : 'total',
        key : 'total'
    },
    {
        title : 'Giá',
        dataIndex : 'priceUnit',
        key : 'priceUnit'
    }
]

export default Rooms;