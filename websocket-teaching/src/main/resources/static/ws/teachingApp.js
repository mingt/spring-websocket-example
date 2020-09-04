var TeachingWsApp = {
    init: function(){
        var app = {};
        app.name = '授课ws Demo';
        app.options = {};

        // 是否使用 RabbitMQ 等外部Broker。如果不是，则为SimpleBroker，为兼容旧版本，名称分隔符沿用 /，否则使用点号.
        app.ifExternalBroker = false;
        // var topicMessage;
        // var topicUser;

        var _self = app;

        // 私有属性
        var stompClient = null;
        var connecting = false;
        var accessToken='';
        var loginName, password;
        var classId, paramX;
        var cmdJson=$('#command').val();
        $('#command').val($.trim(cmdJson));

        app.checkIfExternalBroker = function() {
            var checkAj = $.ajax( {
                url:'/api/wssys/ifExternalBroker',
                // headers : {'Authorization':'Bearer {{access-token}}'}, // post
                // data:{
                //     grant_type:"password",
                //     username:loginName,
                //     password:password
                // },
                type: 'get',
                async : false,
                cache:false,
                dataType:'json',
                success:function(data) {
                    if (data && data.ifExternalBroker === true) {
                        _self.ifExternalBroker = true;
                        // $( "#sendSelfPmOld" ).prop('disabled', false);
                        console.log("ifExternalBroker true");
                    } else {
                        // $( "#sendSelfPmOld" ).prop('disabled', true);
                        console.log("ifExternalBroker false");
                    }
                },
                error : function(e) {
                    alert("获取token异常：" + e.responseText);
                }
            });
        };

        app.setConnected = function(connected) {
            $("#connect").prop("disabled", connected);
            $("#disconnect").prop("disabled", !connected);
            if (connected) {
                // $("#conversation").show();
                $("#reconnect").removeClass("hide");
                connecting = true;
            }
            else {
                // $("#conversation").hide();
                $("#reconnect").addClass("hide");
                connecting = false;
            }
            $("#greetings").html("");
        };

        app.connect = function() {
            accessToken = '';   //连接前清空旧token
            loginName = $.trim($('#loginName').val());
            if (loginName.length === 0) {
                showGreeting('请提供登录名');
                alert('请提供登录名');
                return;
            }
            password = $.trim($('#password').val());
            if (password.length === 0) {
                showGreeting('登录密码未提供，默认使用123456作为登录密码尝试登录');
                alert('未提供登录密码，默认使用123456尝试登录');
                password='123456';
                $('#password').val('123456');
            }
            var aj = $.ajax( {
                url:'/userauth/oauth/token',
                headers : {'Authorization':'Basic YWNtZTphY21lc2VjcmV0'}, // acme
                data:{
                    grant_type: "password",
                    username:loginName,
                    password:password
                },
                type:'post',
                async : false,
                cache:false,
                dataType:'json',
                success:function(data) {
                    accessToken=data.access_token;
                },
                error : function(e) {
                    alert("获取token异常："+e.responseText);
                }
            });
            classId = $.trim($('#classId').val());
            paramX = $.trim($('#paramX').val());
            if (classId.length == 0) {
                showGreeting('请提供 课堂ID');
                return;
            }
            if (accessToken.length == 0) {
                showGreeting('Access token 获取失败！');
                return;
            }
            // if (paramX.length == 0) {
            //     paramX = 'def';
            //     $('#paramX').val(paramX)
            // }

            // 这里必须加context 即 /api 全写则如: https://uat.somedomain.cn/api/wsteaching
            var socket = new SockJS('/api/wsteaching');
            stompClient = Stomp.over(socket);
            stompClient.connect({"X-Auth-Token": accessToken},
                // ok callback
                function (frame) {
                    _self.setConnected(true);
                    console.log('Connected: ' + frame);
                    // 下面这个与后端配置一致 不必要加 context 即不必要 /api
                    // stompClient.subscribe('/topic/greetings', function (greeting) {
                    //     showGreeting(JSON.parse(greeting.body).content);
                    // });
                    // stompClient.subscribe('/user/queue/greetings', function (greeting) {
                    //     showGreeting(JSON.parse(greeting.body).content);
                    // });
                    stompClient.subscribe('/user/queue/message', function (info) {
                        // JSON.parse(msg.body).content
                        showGreeting('/user/queue/message 定向返回结果：' + info.body, 'end');
                    });
                    stompClient.subscribe('/user/queue/errors', function (error) {
                        showGreeting('/user/queue/errors：' + error.body, 'end');
                    });
                    // 通用指令
                    stompClient.subscribe('/topic/cmd', function (result) {
                        showGreeting('/topic/cmd 返回结果：' + result.body);
                        var commandParam = JSON.parse(result.body);
                        if (commandParam) {
                            showGreeting('解释出 classId = ' + commandParam.classId, 'end');
                        } else {
                            showGreeting('返回结果出错了', 'end');
                        }
                    });
                    // 频道
                    if (_self.ifExternalBroker) {
                        var topicTimer = '/topic/timer.' + classId;
                    } else {
                        var topicTimer = '/topic/timer/' + classId;
                    }
                    stompClient.subscribe(topicTimer, function (result) {
                        showGreeting(topicTimer + ' 返回结果：' + result.body);
                        var commandParam = JSON.parse(result.body);
                        if (commandParam) {
                            showGreeting('解释出 classId = ' + commandParam.classId, 'end');
                        } else {
                            showGreeting('返回结果出错了', 'end');
                        }
                    });
                    // 点名
                    if (_self.ifExternalBroker) {
                        var topicCallName = '/topic/callname.' + classId;
                    } else {
                        var topicCallName = '/topic/callname/' + classId;
                    }
                    stompClient.subscribe(topicCallName, function (result) {
                        showGreeting(topicCallName + '返回结果：' + result.body);
                        var commandParam = JSON.parse(result.body);
                        if (commandParam) {
                            showGreeting('解释出 classId = ' + commandParam.classId);
                            var command = commandParam.command;
                            if (!command)  {
                                showGreeting('返回结果有误没有command', 'end');
                                return;
                            }
                            // if (command.type === 'resource') {
                            //
                            // }
                        } else {
                            showGreeting('返回结果有误', 'end');
                        }
                    });
                },
                // error callback
                function (frame) {
                    showGreeting('连接已断开，或请检查 access_token 是否有效: ' + frame, 'end');
                }
            );
        };

        app.disconnect = function() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            _self.setConnected(false);
            console.log("Disconnected");
        };

        app.reconnect = function() {
            // if (stompClient !== null) {
            //     stompClient.disconnect();
            // }
            // setConnected(false);
            // console.log("Disconnected");

            _self.disconnect();
            _self.connect();
        };

        app.timerCommand = function() {
            if (stompClient) {
                var command = getCommand();
                if (!command) {
                    return;
                }
                if (!connecting) {
                    showGreeting('未连接');
                    return;
                }
                // 根据输出指令是完整的commandParam还是只有command
                if (command.command) { // commandParam
                    var body = JSON.stringify(command);
                } else { // command
                    var body = JSON.stringify({
                        "classId": classId,
                        "command": command
                    });
                }
                showGreeting('送出的指令：' + body, 'start');
                // 这个与后端配置一致 不必要加 context 即不必要 /api/app/hello
                stompClient.send("/app/teaching/timer/command", {}, body);
            }
        };
        app.callNameCommand = function() {
            // showGreeting('点名指令 未实现');
            // return;
            if (stompClient) {
                var command = getCommand();
                if (!command) {
                    return;
                }
                if (!connecting) {
                    showGreeting('未连接');
                    return;
                }
                // 根据输出指令是完整的commandParam还是只有command
                if (command.command) { // commandParam
                    var body = JSON.stringify(command);
                } else { // command
                    var body = JSON.stringify({
                        "classId": classId,
                        "command": command
                    });
                }
                showGreeting('送出的指令：' + body, 'start');
                stompClient.send("/app/teaching/callname/command", {}, body);
            }
        };

        app.testGenericOnlyCmd = function() {
            if (stompClient) {
                var command = getCommand();
                if (!command) {
                    return;
                }
                if (!connecting) {
                    showGreeting('未连接');
                    return;
                }
                // 根据输出指令是完整的commandParam还是只有command
                if (command.command) { // commandParam
                    var body = JSON.stringify(command);
                } else { // command
                    var body = JSON.stringify({
                        "classId": classId,
                        "command": command
                    });
                }
                showGreeting('testGenericOnlyCmd 必需的参数只有command.type 送出的指令：' + body, 'start');
                stompClient.send("/app/command", {}, body);
            }
        };
        app.testGenericUsers = function() {
            if (stompClient) {
                var command = getCommand();
                if (!command) {
                    return;
                }
                if (!connecting) {
                    showGreeting('未连接');
                    return;
                }
                // 根据输出指令是完整的commandParam还是只有command
                if (command.command) { // commandParam
                    var body = JSON.stringify(command);
                } else { // command
                    var body = JSON.stringify({
                        "classId": classId,
                        "command": command,
                        "chan": '/user/queue/message',
                        "users": 'xuesheng1'
                    });
                }
                showGreeting('testGenericUsers 提供users 送出的指令：' + body, 'start');
                stompClient.send("/app/command", {}, body);
            }
        };
        app.testGenericSelf = function() {
            if (stompClient) {
                var command = getCommand();
                if (!command) {
                    return;
                }
                if (!connecting) {
                    showGreeting('未连接');
                    return;
                }
                // 根据输出指令是完整的commandParam还是只有command
                if (command.command) { // commandParam
                    var body = JSON.stringify(command);
                } else { // command
                    var body = JSON.stringify({
                        "classId": classId,
                        "command": command,
                        "chan": '/user/queue/message'
                    });
                }
                showGreeting('testGenericSelf 不提供users,但chan以/user/开头 送出的指令：' + body, 'start');
                stompClient.send("/app/command", {}, body);
            }
        };

        // 私有方法
        function showGreeting(message, flag) {
            var trClass = '';
            if (flag === 'end') {
                trClass = 'tr-block-top';
            } else if (flag === 'start') {
                trClass = 'tr-block-bottom';
            }
            $("#greetings").prepend("<tr class='" + trClass + "'><td>" + message + "</td></tr>"); // append
        }
        function getCommand() {
            var cmdStr = $.trim($('#command').val());
            if (cmdStr.length == 0) {
                showGreeting('请提供指令内容,最高层可以是command，也可以的commandParam');
                return false;
            }
            try {
                var result = JSON.parse(cmdStr);
                if (result.command) { // commandParam
                    if (!result.command.timestamp) {
                        result.command.timestamp = new Date().getTime();
                    }
                } else { // command
                    if (!result.timestamp) {
                        result.timestamp = new Date().getTime();
                    }
                }
                return result;
            } catch (e) {
                showGreeting('请检查指令内容，json解释出错');
            }
            return false;
        }

        return app;
    }
};


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    var teachingWsApp = TeachingWsApp.init();

    teachingWsApp.checkIfExternalBroker();

    $( "#connect" ).click(function() { teachingWsApp.connect(); });
    $( "#disconnect" ).click(function() { teachingWsApp.disconnect(); });
    $( "#reconnect" ).click(function() { teachingWsApp.reconnect(); });
    $( "#btn-timer" ).click(function() { teachingWsApp.timerCommand(); });
    $( "#btn-callName" ).click(function() { teachingWsApp.callNameCommand(); });

    $( "#testGenericOnlyCmd" ).click(function() { teachingWsApp.testGenericOnlyCmd(); });
    $( "#testGenericTopic" ).click(function() { teachingWsApp.testGenericTopic(); });
    $( "#testGenericUsers" ).click(function() { teachingWsApp.testGenericUsers(); });
    $( "#testGenericSelf" ).click(function() { teachingWsApp.testGenericSelf(); });
});

