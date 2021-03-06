import React, { useEffect, useState } from 'react';
import { Table, Tag, Space, Tabs, Modal, Button, Form, notification } from 'antd';
import RenderForm from './RenderForm';
import { TwitterOutlined, CloseOutlined, CheckOutlined} from '@ant-design/icons';
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
                notification.open({
                    message: res.data.message,
                    description:
                    <TwitterOutlined style={{color : '#93b874'}}/> ,
                    icon: <CheckOutlined style={{ color: '#108ee9' }} />,    
                  })
            } catch (error) {
                notification.open({
                    message: error.response.data.message,
                    description:
                    <TwitterOutlined style={{color : '#93b874'}}/> ,
                    icon: <CloseOutlined style={{ color: '#e80404' }} />,
                    
                  })
            }
        }

        if(visible.type == "edit") {
            try {
                const res = await apiClient.put(`http://localhost:8080/guest/${visible.data.id}`, values)
                console.log("res" ,res)
                notification.open({
                    message: res.data.message,
                    description:
                    <TwitterOutlined style={{color : '#93b874'}}/> ,
                    icon: <CheckOutlined style={{ color: '#108ee9' }} />,    
                  })
            } catch (error) {
                notification.open({
                    message: error.response.data.message,
                    description:
                    <TwitterOutlined style={{color : '#93b874'}}/> ,
                    icon: <CloseOutlined style={{ color: '#e80404' }} />,                 
                  })
            }
        }
        onCancel()
    };
    return (
        <div>
            <Modal
                title={visible.type == "edit" ? "Ch???nh s???a" : "Th??m m???i"}
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
                            {visible.type == "edit" ? "Ch???nh s???a" : "Th??m m???i"}
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
        label: 'H??? t??n',
        rules: [{ required: true, message: 'Kh??ng ???????c b??? tr???ng' }],
    },
    {
        name: 'birthDate',
        label: 'Ng??y sinh',
        rules: [{ required: true, message: 'Kh??ng ???????c b??? tr???ng' }],
    },
    {
        name: 'identificationNo',
        label: 'S??? CMT',
        rules: [{ required: true, message: 'Kh??ng ???????c b??? tr???ng' }],
    },
    {
        name: 'studentID',
        label: 'ID sinh vi??n',
        rules: [{ required: true, message: 'Kh??ng ???????c b??? tr???ng' }],
    },
    
];

export default ModalForm;