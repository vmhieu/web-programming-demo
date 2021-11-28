import React, { useState } from 'react';
import { Table, Tag, Space, Tabs, Modal, Button, Form } from 'antd';
import RenderForm from './RenderForm';
const ModalForm = ({ visible, onCancel = () => { },
}) => {

    const [dataForm] = Form.useForm();
    const [dataInit, setDataInit] = useState({});

    const [addAccountForm, setAddAccountForm] = useState(addAccountFormInit);

    const onFinish = (values) => {
        console.log('Success:', values);
    };
    return (
        <div>
            <Modal
                title="Chỉnh sửa"
                visible={visible}
                onCancel={() => {
                    onCancel();
                }}
                footer={null}
            >
                <Form
                    form={dataForm}
                    name="control-hooks"
                    onFinish={onFinish}
                    labelCol={{ span: 7 }}
                    wrapperCol={{ span: 17 }}
                    initialValues={dataInit}
                >
                    <RenderForm
                        jsonFrom={addAccountForm}
                        disabled={[]}

                    />
                    <Form.Item style={{ marginTop: 20, paddingTop: 10, borderTop: '1px solid #ddd', marginRight: 15 }} wrapperCol={{ span: 24 }} >
                        <Button type="primary" htmlType="submit" style={{ float: 'right' }} loading={false}>
                            Chỉnh sửa
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    );
}

const addAccountFormInit = [
    {
        name: 'username',
        label: 'Tên đăng nhập',
        rules: [{ required: true, message: 'Tên đăng nhập cần có ít nhất 3 ký tự', min: 3 }],
        // type: 'number'
    },
    {
        name: 'name',
        label: 'Tên',
        rules: [{ required: true, message: 'Bạn cần nhập tên tài khoản' }],
    },
    {
        name: 'password',
        label: 'Mật khẩu',
        type: 'password',
        rules: [{
            required: true,
            message: 'Mật khẩu cần có ít nhất 6 ký tự',
            min: 6
        },],
    },
    {
        name: 'email',
        label: 'Email',
        rules: [{ required: true, type: 'email', message: 'Địa chỉ email không đúng định dạng' }],
    },
    {
        name: 'phone',
        label: 'Số điện thoại',
        type: 'phone',
        rules: [{ required: true, message: 'Bạn cần điền số điện thoại' }],
    },
    {
        name: 'level',
        label: 'Role',
        type: 'select',
        rules: [{ required: true, message: 'Bạn cần chọn role' }],
        data: []
    },
    {
        name: 'line_id',
        label: 'Line',
        type: 'select',
        rules: [{ required: true, message: 'Bạn cần chọn Line' }],
        data: []
    },
];
export default ModalForm;