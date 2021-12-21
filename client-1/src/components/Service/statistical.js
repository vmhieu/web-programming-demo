import React, { useEffect, useState } from 'react';
import { apiClient } from '../../service/apiClient';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
  } from 'chart.js';
  import { Bar } from 'react-chartjs-2';

import { LeftCircleOutlined, StarFilled, StarTwoTone } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';
const Statistical = () => {

    const [food ,setFood] = useState('');
    const [laundry , setLaundry] = useState('');
    const [parking , setParking] = useState('')
    const [room , setRoom] = useState('')

    const navigation = useNavigate()
    const _requestData = async () => {
        try {
            const resFood = await apiClient.get("http://localhost:8080/api/service/bill/food");
            const resLaundry = await apiClient.get("http://localhost:8080/api/service/bill/laundry");
            const resParking = await apiClient.get("http://localhost:8080/api/service/bill/parking")
            const resRoom = await apiClient.get("http://localhost:8080/api/room/bill")


            setFood(resFood.data.data)
            setParking(resParking.data.data)
            setLaundry(resLaundry.data.data)
            setRoom(resRoom.data.data)
        } catch (error) {
            console.log("err " ,error);
        }
    }

    ChartJS.register(
        CategoryScale,
        LinearScale,
        BarElement,
        Title,
        Tooltip,
        Legend
      );
      
      const options = {
        responsive: true,
        plugins: {
          legend: {
            display : false
          },
          title: {
            display: true,
            text: 'Thống kê',
            font: {size : 26}
          },
        },
      };
      const labels = ["Đồ ăn" , "Gửi xe" , "Giặt là" , "Phòng"];
      const data = {
        labels,
        datasets: [
          {
            label: 'Dataset 1',
            data: [food , parking , laundry ,room],
            backgroundColor: 'rgba(255, 99, 132, 0.5)',
          },
        ],
      };

    useEffect(() => {
        _requestData()
    },[])

    return (
        <div style={{maxWidth : '60%' , maxHeight : '60%' , margin : 'auto' , marginTop : '7%'}}>
            <LeftCircleOutlined style={{fontSize  : '42px'}} onClick={() => navigation('/home')}/>
          <Bar options={options} data={data} />;
        </div>
    );
};

export default Statistical;