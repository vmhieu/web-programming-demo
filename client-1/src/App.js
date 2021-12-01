import axios from "axios";
import { Table, Tag, Space, Tabs, Button } from 'antd';
import Student from "./components/student";
import Rooms from "./components/rooms";
import Visiter from "./components/visiter";
import Service from "./components/service";
import { useEffect } from "react";
import { PlusOutlined, ReloadOutlined } from '@ant-design/icons';
function App() {




  const { TabPane } = Tabs;

  // const Demo = () => (
  //   <Tabs defaultActiveKey="1" centered>
  //     <TabPane tab="Tab 1" key="1">
  //       Content of Tab Pane 1
  //     </TabPane>
  //     <TabPane tab="Tab 2" key="2">
  //       Content of Tab Pane 2
  //     </TabPane>
  //     <TabPane tab="Tab 3" key="3">
  //       Content of Tab Pane 3
  //     </TabPane>
  //   </Tabs>
  // );


  return (
    <div className="App">
      <Tabs defaultActiveKey="1" centered>
        <TabPane tab="Quản lý sinh viên" key="1">
          <Student />
        </TabPane>
        <TabPane tab="Quản lý khách" key="2">
          <Visiter />
        </TabPane>
        <TabPane tab="Quản lý phòng" key="3">
          <Rooms />
        </TabPane>
        <TabPane tab="Quản lý dịch vụ" key="4">
          <Service />
        </TabPane>
      </Tabs>

    </div>
  );
}


export default App;
