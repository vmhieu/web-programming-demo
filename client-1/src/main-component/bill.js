import { useParams } from "react-router";
import { useSelector } from 'react-redux';
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import "./bill.css";

const Bill = (props) => {
  const navigate = useNavigate();
  const select = useSelector(state => state.login[0].isLogin);
  
  // useEffect(() => {
  //   console.log(select);
  //   if (!select) {
  //     navigate('/');
  //   }
  // }, []);
  
  const { id } = useParams();
  const data = {
    '2021-12-08': [ { name: 'Gửi xe-HD981(FREE)', price: 0 } ],
    '2021-12-09': [
      { name: 'Gửi xe-HD981', price: 3000 },
      { name: 'Gửi xe-HD981', price: 3000 },
      { name: 'Gửi xe-HD981', price: 3000 },
      { name: 'Đồ ăn-HD981(x2)', price: 3000 },
      { name: 'Giặt là-HD981(2kg)', price: 3000 }
    ]
  }

  return (
    <div className="container pt-5">
      <div className="columns is-centered">
        <div className="column is-half">
          <div className="card">
            <div className="card-content">
              <h1 className="title is-4 has-text-centered">HÓA ĐƠN THÁNG 12/2021 </h1>
              <p>Họ và tên: Trịnh Vũ Đức</p>
              <p>Mã SV: B18DCCN100</p>
              <p>Tiền dịch vụ:</p>
              <hr className="divider" />
              <div className="columns">
                <div className="column">
                  <span className="title is-6">Ngày</span>
                </div>
                <div className="column">
                  <span className="title is-6">Sản phẩm</span>
                </div>
                <div className="column has-text-right">
                  <span className="title is-6">Giá trị</span>
                </div>
              </div>
              {Object.keys(data).map((day, index) => {
                return (
                  <>
                    <hr className="divider" />
                    <div key={index} className="columns">
                      <div className="column is-one-third">{day}</div>
                      <div className="column">
                        {data[day].map((item, index) => {
                          return (
                            <div className="columns">
                              <div key={index} className="column">
                                {item.name}
                              </div>
                              <div key={index} className="column has-text-right">
                                {item.price}
                              </div>
                            </div>
                          );
                        })}
                      </div>
                    </div>
                  </>
                );
              })}
              <hr className="divider" />
              <div className="columns">
                <div className="column"></div>
                <div className="column">
                  <span>Tiền phòng</span>
                </div>
                <div className="column has-text-right">
                  <span>650.000</span>
                </div>
              </div>
              <div className="columns">
                <div className="column"></div>
                <div className="column">
                  <span>Tiền gửi xe vé tháng</span>
                </div>
                <div className="column has-text-right">
                  <span>100.000</span>
                </div>
              </div>
              <hr className="divider" />
              <div className="columns">
                <div className="column"></div>
                <div className="column">
                  <span className="title is-6">Tổng cộng</span>
                </div>
                <div className="column has-text-right">
                  <span className="title is-6">650.000</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Bill;