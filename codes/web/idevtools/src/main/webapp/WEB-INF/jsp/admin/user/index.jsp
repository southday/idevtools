<%--
  Created by IntelliJ IDEA.
  User: 王沁宽
  Date: 2019/2/18
  Time: 17:52
  该界面用于显示所有的用户信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" />
    <title>用户管理主界面</title>

</head>
<body>
    <%--用户基本信息表--%>
    <table class="table">
        <thead>
        <tr>
            <th>航班号</th>
            <th>始发站</th>
            <th>终点站</th>
            <th>出发时间</th>
            <th>总量</th>
            <th>余量</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="flight in info.data">
            <td v-text="flight.flightNum"/>
            <td v-text="flight.fromCity"/>
            <td v-text="flight.arivCity"/>
            <td v-text="flight.startDate"/>
            <td v-text="flight.seatNum"/>
            <td v-text="flight.availNum"/>
            <td v-text="flight.price"/>
            <td><a :href="'http://localhost:8080/TravelOrder/home/orderFlight?flight_id='+flight.flightId">购买</a></td>
            <td><a :href="'http://localhost:8080/TravelOrder/home/delete?flight_id='+flight.flightId">删除</a></td>
        </tr>
        </tbody>
    </table>
</body>


<script>
    /*vue初始化*/
    new Vue({
        el: '#app',
        data () {
            return {
                //保存用户信息的json数据
                info: null
            }
        },
        //获取userinfo.json数据，保存到info中
        mounted () {
            axios
                .get('http://localhost:8080/idevtools_war/user/userinfo.json')
                .then(response => (this.info = response))
        }
    })


</script>


</html>
