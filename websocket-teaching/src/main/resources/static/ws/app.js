var stompClient = null;
var accessToken;
var type, id;

// 是否使用 RabbitMQ 等外部Broker。如果不是，则为SimpleBroker，为兼容旧版本，名称分隔符沿用 /，否则使用点号.
var ifExternalBroker = false;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        // $("#conversation").show();
        $("#reconnect").removeClass("hide");
    }
    else {
        // $("#conversation").hide();
        $("#reconnect").addClass("hide");
    }
    $("#greetings").html("");
}

function connect() {
    accessToken = $.trim($('#access-token').val());
    if (accessToken.length == 0) {
        showGreeting('请提供 access token');
        return;
    }
    type = $.trim($('#type').val());
    id = $.trim($('#id').val());
    if (type.length == 0) {
        type = 'abc';
        $('#type').val(type)
    }
    if (id.length == 0) {
        id = 'def';
        $('#id').val(id)
    }
    $('#channel-id').empty().append(type + '/' + id);
    var socket = new SockJS('/api/wschat'); // 这里必须加context 即 /api // gs-guide-websocket
    stompClient = Stomp.over(socket);
    stompClient.connect({"X-Auth-Token": accessToken},
        function (frame) {
            setConnected(true);
            $('#current-user').text(frame.headers["user-name"]);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) { // 这个与后端配置一致 不必要加 context 即不必要 /api
                console.log('/topic/greetings');
                try {
                    var content = JSON.parse(greeting.body).content;
                    if (content) {
                        showGreeting(content);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }

            });
            // rabbitmq
            stompClient.subscribe('/amq/queue/test', function (greeting) {
                console.log('/amq/queue/test');
                try {
                    var content = JSON.parse(greeting.body).content;
                    if (content) {
                        showGreeting(content);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }
            });
            // rabbitmq
            stompClient.subscribe('/user/amq/queue/test', function (greeting) {
                console.log('/user/amq/queue/test');
                try {
                    var content = JSON.parse(greeting.body).content;
                    if (content) {
                        showGreeting(content);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }
            });
            // rabbitmq
            stompClient.subscribe('/user/exchange/amq.direct/greetings', function (greeting) {
                console.log('/user/exchange/amq.direct/greetings');
                try {
                    var content = JSON.parse(greeting.body).content;
                    if (content) {
                        showGreeting(content);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }
            });
            // rabbitmq
            stompClient.subscribe('/exchange/amq.direct/greetings', function (greeting) {
                console.log('/exchange/amq.direct/greetings');
                try {
                    var content = JSON.parse(greeting.body).content;
                    if (content) {
                        showGreeting(content);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }
            });
            // simpleBroker
            stompClient.subscribe('/user/queue/greetings', function (greeting) {
                console.log('/user/queue/greetings');
                try {
                    var content = JSON.parse(greeting.body).content;
                    if (content) {
                        showGreeting(content);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }
            });
            // rabbitmq
            stompClient.subscribe('/user/exchange/amq.direct/errors', function (greeting) {
                console.log('/user/exchange/amq.direct/errors');
                try {
                    if (greeting.body) {
                        showGreeting(greeting.body);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }
            });
            stompClient.subscribe('/user/queue/errors', function (greeting) {
                console.log('/user/queue/errors');
                try {
                    if (greeting.body) {
                        showGreeting(greeting.body);
                    }
                } catch (ex) {
                    console.log(greeting.body);
                }
            });
            // 频道
            // stompClient.subscribe('/topic/messages/' + type + '/' + id, function (msg) {
            if (ifExternalBroker) {
                var topicMessage = '/topic/messages.' + type + '.' + id;
            } else {
                var topicMessage = '/topic/messages/' + type + '/' + id;
            }
            stompClient.subscribe(topicMessage, function (msg) {
                console.log(topicMessage);
                try {
                    var content = JSON.parse(msg.body).content;
                    if (content) {
                        showGreeting(content);
                    }
                } catch (ex) {
                    console.log(msg.body);
                }
            });
            // stompClient.subscribe('/topic/users/' + type + '/' + id, function (result) {
            if (ifExternalBroker) {
                var topicUser = '/topic/users.' + type + '.' + id;
            } else {
                var topicUser = '/topic/users/' + type + '/' + id;
            }
            stompClient.subscribe(topicUser, function (result) {
                console.log(topicUser);
                var users;
                try {
                    users = JSON.parse(result.body);
                } catch (ex) {
                    console.log(result.body);
                    return;
                }
                var userNames = '用户列表: ';
                for (var i=0, j=users.length; i<j; i++) {
                    userNames += users[i].name + ' ';
                }
                showGreeting(userNames);
            });
        },
        function (frame) {
            $('#current-user').text('(未连接)');
            showGreeting('连接已断开，或请检查 access_token 是否有效: ' + frame);
        }
    );
}

function disconnect() {
    if (stompClient !== null) {

        // 尝试断开连接前先 unsubcribe ，使得后端有 unsubcribe 事件
        if (ifExternalBroker) {
            var topicMessage = '/topic/messages.' + type + '.' + id;
        } else {
            var topicMessage = '/topic/messages/' + type + '/' + id;
        }
        stompClient.unsubscribe(topicMessage);

        stompClient.disconnect();
    }
    setConnected(false);
    $('#current-user').text('(未连接)');
    console.log("Disconnected");
}

function reconnect() {
    // if (stompClient !== null) {
    //     stompClient.disconnect();
    // }
    // setConnected(false);
    // console.log("Disconnected");

    disconnect();
    connect();
}

function sendName() {
    // 这个与后端配置一致 不必要加 context 即不必要 /api/app/hello
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendNameSelf() {
    // 这个与后端配置一致 不必要加 context 即不必要 /api/app/hello
    stompClient.send("/app/pm", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendNameSelfPmOld() {
    stompClient.send("/app/pmOld", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendToChannel() {
    stompClient.send("/app/chat/sendMsg", {}, JSON.stringify({'type': type, 'id': id, 'content': $("#name").val()}));
}

function updateUsers() {
    stompClient.send("/app/publicChat/users", {}, JSON.stringify({'type': type, 'id': id}));
}

function showGreeting(message) {
    $("#greetings").prepend("<tr><td>" + message + "</td></tr>"); // append
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#reconnect" ).click(function() { reconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#sendSelf" ).click(function() { sendNameSelf(); });
    $( "#sendSelfPmOld" ).click(function() { sendNameSelfPmOld(); });
    $( "#sendToChannel" ).click(function() { sendToChannel(); });
    $( "#testPublicUsers" ).click(function() { updateUsers(); });
});

