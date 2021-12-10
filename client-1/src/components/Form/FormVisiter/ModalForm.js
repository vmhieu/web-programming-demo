import React, { useEffect, useState } from 'react';
import { Table, Tag, Space, Tabs, Modal, Button, Form } from 'antd';
import RenderForm from './RenderForm';
import {addGuest, addStudent} from '../../../service/account'
import { apiClient } from '../../../service/apiClient';
const ModalForm = ({ visible, onCancel = () => { },
}) => {
    console.log('visible' ,visible)
    const [form] = Form.useForm();
    const [dataInit, setDataInit] = useState({});

    useEffect(() => form.resetFields(), [dataInit]);

    const [addAccountForm, setAddAccountForm] = useState(addAccountFormInit);
    useEffect(() => {
        if(visible.type == "add"){
            setDataInit({})
        }
        if(visible.type == "edit"){
            setDataInit(visible.data)
        }
    } , [visible])


    const onFinish = async(values) => {
        console.log('Success:', values);
        if(visible.type == "add") {
            
            try {
                const res = await addGuest(values)
                // if(Response.message === "Số người trong phòng đã tối đa"){
                //     window.alert("Số người trong phòng đã tối đa")
                // }
                console.log("res" ,res)
            } catch (error) {
                console.log("err" ,error)
            }
        }

        if(visible.type == "edit") {
            try {
                const res = await apiClient.put(`http://localhost:8080/api/guest/${visible.data.id}`, values)
                console.log("res" ,res)
            } catch (error) {
                console.log("err" ,error)
            }
        }
        onCancel();
    };
    return (
        <div>
            <Modal
                title={visible.type == "edit" ? "Chỉnh sửa" : "Thêm mới"}
                visible={visible}
                onCancel={() => {
                    console.log("123")
                    onCancel();
                }}
                footer={null}
            >
                <Form
                    form={form}
                    name="control-hooks"
                    onFinish={onFinish}
                    labelCol={{ span: 7 }}
                    wrapperCol={{ span: 17 }}
                    initialValues={dataInit} 
                >
                    <RenderForm
                        jsonFrom={addAccountForm}
                        visible={visible}
                    />
                    <Form.Item style={{ marginTop: 20, paddingTop: 10, borderTop: '1px solid #ddd', marginRight: 15 }} wrapperCol={{ span: 24 }} >
                        <Button type="primary" htmlType="submit" style={{ float: 'right' }} loading={false}>
                            {visible.type == "edit" ? "Chỉnh sửa" : "Thêm mới"}
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    );
}

const addAccountFormInit = [
    {
        name: 'name',
        label: 'Name',
        rules: [{ required: true, message: 'Không được bỏ trống' }],
        // type: 'number'
    },
    {
        name: 'birthDate',
        label: 'BirthDate',
        rules: [{ required: true, message: 'Không được bỏ trống' }],
    },
    // {
    //     name: 'Ngày đến',
    //     label: 'Date',
    //     rules: [{required : true ,message : "Không được bỏ trống"}],
    // },
    {
        name: 'identificationNo',
        label: 'CMT',
        rules: [{ required: true, message: 'Không được bỏ trống' }],
    },
    {
        name: 'studentID',
        label: 'StudentID',
        rules: [{ required: true, message: 'Không được bỏ trống' }],
    },
    
];

export default ModalForm;