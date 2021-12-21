import React, { useEffect, useState } from "react";
import { Table, Tag, Space, Tabs, Modal, Button, Form, notification } from "antd";
import { TwitterOutlined, CloseOutlined, CheckOutlined} from '@ant-design/icons';
import RenderForm from "./RenderForm";
import { addLaundry, addStudent, editStudent } from "../../../service/account";
import { apiClient } from "../../../service/apiClient";
const ModalForm = ({ visible, onCancel = () => {} }) => {
  console.log("visible", visible);
  const [form] = Form.useForm();
  const [dataInit, setDataInit] = useState({});

  useEffect(() => form.resetFields(), [dataInit]);

  const [addAccountForm, setAddAccountForm] = useState(addAccountFormInit);
  useEffect(() => {
    if (visible.type == "add") {
      setDataInit({});
    }
    if (visible.type == "edit") {
      setDataInit(visible.data);
    }
  }, [visible]);

  const onFinish = async (values) => {
    console.log("Success:", values);
    if (visible.type == "add") {
      try {
        const res = await addLaundry(values);
        notification.open({
          message: res.data.message,
          description:
          <TwitterOutlined style={{color : '#93b874'}}/> ,
          icon: <CheckOutlined style={{ color: '#108ee9' }} />,    
        })
        console.log("res", res);
      } catch (error) {
        notification.open({
          message: error.response.data.message,
          description:
          <TwitterOutlined style={{color : '#93b874'}}/> ,
          icon: <CloseOutlined style={{ color: '#e80404' }} />,
          
        })
      }
    }
    if (visible.type == "edit") {
        try {
          const res = await apiClient.put(`http://localhost:8080/api/service/laundry/${visible.data.id}`, values);
          notification.open({
            message: res.data.message,
            description:
            <TwitterOutlined style={{color : '#93b874'}}/> ,
            icon: <CheckOutlined style={{ color: '#108ee9' }} />,    
          })
          console.log("res", res);
        } catch (error) {
          notification.open({
            message: "Sửa thất bại",
            description:
            <TwitterOutlined style={{color : '#93b874'}}/> ,
            icon: <CloseOutlined style={{ color: '#e80404' }} />,
          })
          console.log("err", error);
        }
        console.log("===", {
            id: visible.data.id,
            ...values
        })
      }
     onCancel();
  };
  return (
    <div>
      <Modal
        title={visible.type == "edit" ? "Chỉnh sửa" : "Thêm mới"}
        visible={visible}
        onCancel={() => {
          console.log("123");
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
          <RenderForm jsonFrom={addAccountForm} visible={visible} />
          <Form.Item
            style={{
              marginTop: 20,
              paddingTop: 10,
              borderTop: "1px solid #ddd",
              marginRight: 15,
            }}
            wrapperCol={{ span: 24 }}
          >
            <Button
              type="primary"
              htmlType="submit"
              style={{ float: "right" }}
              loading={false}
            >
              {visible.type == "edit" ? "Chỉnh sửa" : "Thêm mới"}
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

const addAccountFormInit = [
  {
    name: "name",
    label: "Tên dịch vụ",
    rules: [{ required: true, message: "Không được bỏ trống" }],
    // type: 'number'
  },
  {
    name: "serviceCode",
    label: "Mã dịch vụ",
    rules: [{ required: true, message: "Không được bỏ trống" }],
  },
  {
    name: "priceUnit",
    label: "Đơn giá",
    rules: [{ required: true, message: "Không được bỏ trống" }],
  },
  {
    name: "weight",
    label: "Số cân",
    rules: [{ required: true, message: "Không được bỏ trống" }],
  },
  {
    name: "studentId",
    label: "ID Sinh viên",
    rules: [{ required: true, message: "Không được bỏ trống" }],
  },

];

export default ModalForm;
