<template>
    <div class="main-container">
        <nav class="navbar navbar-light" style="background: rgb(173, 177, 237);">
            <a class="navbar-brand" style="color: #ffffff">Chat</a>
        </nav>
        <div class="container">
            <ul id="messages">
                <li v-for="messageDto in ChatMessages" :key="messageDto.id">
                    <div class="text-left">
                        <h6 style="color: #95cdf0">{{messageDto.author}}</h6><h5>{{messageDto.text}}</h5>
                    </div>
                </li>
            </ul>
            <div class="row" style="bottom: 3%;right: 2%;left: 5%; position: fixed">
                <input class="col-10 themed-grid-col" required type="text"
                       placeholder="Type your message" v-model="message">
                <div class="col-2 text-right" style="padding: 15px;">
                    <button class="btn btn-outline-success" style="color: #000000" @click="sendMessage">Send</button>
                </div>
            </div>
        </div>
        <nav class="navbar fixed-bottom navbar-expand-sm navbar-light" style="background: #adb1ed"></nav>
    </div>

</template>

<script>
    import axios from 'axios'

    export default {
        name: 'Chat',
        data: function () {
            return {
                message: '',
                ChatMessages: []
            }
        },
        mounted: function () {
            if (window.localStorage.getItem("AUTH") !== null) {
                axios({
                    url: window.hostname + '/getAll',
                    method: "get"
                }).then((response) => {
                    // eslint-disable-next-line no-console
                    console.log(response);
                    for (var i = 0; i < response.data.length; i++) {
                        this.ChatMessages.unshift((response.data)[i]);
                    }
                });
                axios({
                    url: window.hostname + '/messages',
                    method: "post",
                    data: {
                        'text': null
                    },
                    headers: {
                        "AUTH": window.localStorage.getItem("AUTH")
                    }
                    // eslint-disable-next-line no-unused-vars
                }).then((response) => {
                    this.receiveMessage();
                });
            } else {
                window.location.href = '/login';
            }
        },
        methods: {
            receiveMessage: function () {
                if (window.localStorage.getItem("AUTH") !== null) {
                    axios({
                        url: window.hostname + "/messages",
                        method: "get",
                        headers: {
                            "AUTH": window.localStorage.getItem("AUTH")
                        }
                    }).then((response) => {
                        // eslint-disable-next-line no-console
                        console.log(response);
                        if (response.status == 200) {
                            // eslint-disable-next-line no-console
                            console.log("receivedMessage" + response.data[0].text);
                            this.ChatMessages.unshift(response.data[0]);
                            this.receiveMessage();
                        }
                    });
                } else {
                    window.location.href = '/login';
                }
            },
            sendMessage: function () {
                if (window.localStorage.getItem("AUTH") !== null) {
                    axios({
                        url: window.hostname + "/messages",
                        method: "post",
                        data: {
                            'text': this.message
                        },
                        headers: {
                            "AUTH": window.localStorage.getItem("AUTH")
                        }
                    }).then((response) => {
                        if (response.status == 200) {
                            // eslint-disable-next-line no-console
                            console.log("sendMessage" + response.data[0].message);
                            // eslint-disable-next-line no-console
                            console.log(response);
                        }
                    });
                } else {
                    window.location.href = '/login';
                }
            }

        }
    }
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style type="text/css">
    @import '../assets/style.css';
</style>



