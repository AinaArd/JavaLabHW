<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
</head>

<body>
<div id="app">
    <div class="container">
        <div class="col-lg-6 offset-lg-3">

            <div v-if="ready">
                <p v-for="user in info">
                    {{user.username}} {{user.type}}
                </p>
            </div>

            <div v-if="!ready">
                <h4>Enter your username</h4>
                <form @submit.prevent="addUser">
                    <div class="form-group row">
                        <input type="text" class="form-control col-9" v-model="username"
                               placeholder="Enter username here">
                        <input type="submit" value="Join" class="btn btn-sm btn-info ml-1">

                    </div>
                </form>
            </div>
            <h2 v-else>{{username}}</h2>
            <div class="card bg-info" v-if="ready">
                <div class="card-header text-white">
                    <h4>My Chat App <span class="float-right">{{connections}} connections</span></h4>
                </div>
                <ul class="list-group list-group-flush text-right">
                    <small v-if="typing" class="text-white">{{typing}} is typing</small>
                    <li class="list-group-item" v-for="message in messages">
                            <span :class="{'float-left':message.type === 1}">
                                {{message.message}}
                                <small>:{{message.user}}</small>
                            </span>
                    </li>
                </ul>

                <div class="card-body">
                    <form @submit.prevent="send">
                        <div class="form-group">
                            <input type="text" class="form-control" v-model="newMessage"
                                   placeholder="Enter message here">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.10/vue.js"></script>
<script src="/socket.io/socket.io.js"></script>

<script>
    var socket = io();
    let vue = new Vue({
        el: '#app',

        data: {
            newMessage: null,
            messages: [],
            typing: false,
            username: null,
            ready: false,
            info: [],
            connections: 0,
        },

        created() {
            window.onbeforeunload = () => {
                socket.emit('leave', this.username);
            }

            socket.on('chat-message', (data) => {
                this.messages.push({
                    message: data.message,
                    type: 1,
                    user: data.user,
                });
            });

            socket.on('typing', (data) => {
                this.typing = data;
            });


            socket.on('stopTyping', () => {
                this.typing = false;
            });

            socket.on('joined', (data) => {
                this.info.push({
                    username: data,
                    type: 'joined'
                });


            });

            socket.on('leave', (data) => {
                this.info.push({
                    username: data,
                    type: 'left'
                });


            });

            socket.on('connections', (data) => {
                this.connections = data;
            });
        },

        watch: {
            newMessage(value) {
                value ? socket.emit('typing', this.username) : socket.emit('stopTyping')
            }
        },

        methods: {
            send() {
                this.messages.push({
                    message: this.newMessage,
                    type: 0,
                    user: 'Me',
                });

                socket.emit('chat-message', {
                    message: this.newMessage,
                    user: this.username
                });
                this.newMessage = null;
            },

            addUser() {
                this.ready = true;
                socket.emit('joined', this.username)
            }
        },

    });
</script>

</html>