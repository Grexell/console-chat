messages = [];
var loginForm;
var networkWorker = {
    getData: function (url, params, callback) {
        fetch(url, params).then(callback);
    }
};

function HistoryItem(date, user, text) {
    this.date = date;
    this.user = user;
    this.text = text;
}

var currentUser = {
    username: "client",
    role: "CLIENT"
};

function messageRender(message) {
    var msgBox = "<div class=\"message-item\">\n" +
        "            <div class=\"message-time\">" + message.date + "</div>\n" +
        "            <div class=\"message-sender\">" + message.user.username + "</div>\n" +
        "            <div class=\"message-text\">" + message.text + "</div>\n" +
        "        </div>";

    return msgBox;
}

function messagesRender(messages, clear) {
    var chatBox = document.querySelector(".message-history");

    if (clear) {
        chatBox.innerHTML = "";
    }

    for (var i = 0; i < messages.length; i += 1) {
        chatBox.innerHTML += messageRender(messages[i]);
    }
}

function chatDestroy() {
    document.querySelector(".message-history").remove();
    document.querySelector(".elements").remove();
    document.querySelector(".login").style.visibility = "visible";
}

function chatRender() {
    document.body.innerHTML += "<div class=\"message-history\">\n" +
        "    </div>\n" +
        "\n" +
        "    <div class=\"elements\">\n" +
        "        <form class='chat-form'>\n" +
        "            <button type='button' class=\"chat-close\">X</button>\n" +
        "            <button type='button' class=\"chat-leave\">Leave</button>\n" +
        "            <input class=\"current-text\" />\n" +
        "            <button type='button' class=\"chat-send\">Send</button>\n" +
        "        </form>\n" +
        "    </div>";

    document.querySelector(".chat-close").onclick = function () {
        networkWorker.getData('/api/' + currentUser.id + '/exit', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            }
        }, function (response) {
        });
        chatDestroy();
        clearInterval(messagesUpdate);
    };
    document.querySelector(".chat-leave").onclick = function () {
        networkWorker.getData('/api/' + currentUser.id + '/leave', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(currentUser)
        }, function (response) {
        });
    };
    document.querySelector(".chat-send").onclick = messageSend;
}

function messageSend() {
    var text = document.querySelector(".current-text");
    var sendDate = new Date();
    var item = new HistoryItem(sendDate, currentUser, text.value);

    networkWorker.getData('/api/' + currentUser.id + '/send', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item)
    }, function (response) {
    });

    messagesRender([item]);

    text.value = "";
    event.preventDefault();
}

function messagesUpdate() {
    networkWorker.getData('/api/' + currentUser.id + '/messages', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        }
    }, function (response) {
        response.json().then(
            function (body) {
                messagesRender(body);
            });
    });
}

window.onload = function () {
    loginForm = this.document.querySelector(".login");
    loginForm.onsubmit = function (event) {
        this.style.visibility = "hidden";
        currentUser.username = document.querySelector(".login-box").value;

        networkWorker.getData('/api/register', {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(currentUser)
            }, function (response) {
                response.text().then(
                    function (data) {
                        currentUser.id = data;
                    });
                setInterval(messagesUpdate, 1000);
                chatRender();
            }
        );
        event.preventDefault();
    };
};
