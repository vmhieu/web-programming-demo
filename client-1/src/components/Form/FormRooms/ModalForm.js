import React, { useEffect, useState } from 'react';
import { Table, Tag, Space, Tabs, Modal, Button, Form } from 'antd';
import RenderForm from './RenderForm';
import {addRooms, addStudent} from '../../../service/account'
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
        onCancel()
        console.log('Success:', values);
        if(visible.type == "add") {
            try {
                const res = await addRooms(values)
                console.log("res" ,res)
            } catch (error) {
                console.log("err" ,error)
            }
        }
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
        name: 'type',
        label: 'Kiểu phòng',
        rules: [{required : true ,message : "Không được bỏ trống"}],
    },
    {
        name: 'priceUnit',
        label: 'Đơn giá',
        rules: [{ required: true, message: 'Không được bỏ trống' }],
    },
    {
        name: 'maximum',
        label: 'Số người tối đa',
        rules: [{required : true ,message : "Không được bỏ trống"}],
    },    
];

export default ModalForm;