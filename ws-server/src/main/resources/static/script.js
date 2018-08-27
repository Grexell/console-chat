messages = [];
var loginForm;
var networkWorker = {
    getData: function (url, params, callback) {
        fetch(url, params).then(callback);
    }
}

function HistoryItem(date, user, text) {
    this.date = date;
    this.user = user;
    this.text = text;
}

var currentUser = {
    username: "client",
    role: "CLIENT"
}

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
        networkWorker.getData('/' + currentUser.id + '/exit', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(currentUser)
        }, function (response) {
        });
    };
    document.querySelector(".chat-leave").onclick = function () {
        networkWorker.getData('/' + currentUser.id + '/leave', {
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

    networkWorker.getData('/' + currentUser.id + '/send', {
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
    networkWorker.getData('/' + currentUser.id + '/messages', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        }
    }, function (response) {
        response.json().then(
            function (json) {
                messagesRender(json);
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
                response.json().then(
                    function (json) {
                        currentUser.id = json.data;
                    });
                setInterval(messagesUpdate, 1000);
            }
        );
        chatRender();
        event.preventDefault();
    };
};

// messages = [];
// minimized = true;

// right = "right";
// left = "left";
// chatSide = window.right;
// userIdKey = "username";
// minimizedKey = "minimized";
// sideKey = "side";

// historyItemClass = "message-item";
// messageTimeClass = "message-time";
// messageSenderClass = "message-sender";
// messageHistoryClass = "message-history";
// messageFormClass = "chat-message";
// minimizeButtonClass = "minimize-button";
// chatContentClass = "chat-content";
// hiddenClass = "hidden";
// dragClass = "drag";
// messageTextClass = "message-text";
// authFormClass = "auth-form";
// messageSender = "Я";
// dateDelimeter = ":";

// usersPath = "users";
// messagesPath = "messages";
// suffix = ".json";

// username = messageSender;
// userid = "";
// urlAPI = "";
// time = true;

// answer = {
//     insertionRegExp: /\[.*\]/,
//     pattern: "Ответ на \"{[]}\"",
//     sender: "Бот",
//     delay: 15000
// };

// messagesUpdateTimeout = 10000;

// function printItems(items, clear) {
//     var history = document.getElementsByClassName(messageHistoryClass)[0];
//     var i;

//     if (clear) {
//         history.innerHTML = "";
//     }

//     for (i = 0; i < items.length; i += 1) {
//         history.appendChild(formatItem(items[i]));
//     }
// }

// function authoriseFetch() {
//     fetch(urlAPI + usersPath + suffix, {
//         method: 'POST',
//         body: JSON.stringify({
//             username: window.username
//         }),
//         headers: {
//             Accept: 'application/json',
//             'Content-Type': 'application/json',
//         },
//     })
//         .then(function (response) {
//             return response.json();
//         })
//         .then(function (json) {
//             userid = json.name;
//         });

//     return false;
// }

// function getUserInfoFetch() {
//     window.fetch(urlAPI + usersPath + "/" + userid + "/" + suffix, {
//         headers: {
//             Accept: 'application/json',
//             'Content-Type': 'application/json',
//         },
//     })
//         .then(function (response) {
//             return response.json();
//         })
//         .then(function (json) {
//             username = json.username;
//         });
// }

// function sendMessageFetch(message) {
//     fetch(urlAPI + usersPath + "/" + userid + "/" + messagesPath + "/" + suffix, {
//         method: "POST",
//         body: JSON.stringify(message),
//         headers: {
//             Accept: 'application/json',
//             'Content-Type': 'application/json',
//         },
//     })
//         .then(function (response) {
//             return response.json();
//         });
// }

// function messageUpdateFetch() {
//     var clear = false;

//     fetch(urlAPI + usersPath + "/" + userid + "/" + messagesPath + suffix, {
//         headers: {
//             Accept: 'application/json',
//             'Content-Type': 'application/json',
//         },
//     })
//         .then(function (response) {
//             return response.json();
//         })
//         .then(function (json) {
//             messages = [];

//             if (json) {
//                 Object.keys(json).forEach(function (element) {
//                     messages.push(json[element]);
//                 });

//                 printItems(messages, clear);
//             }
//         });
// }

// fetchNetwork = {
//     authorize: authoriseFetch,
//     getInfo: getUserInfoFetch,
//     sendMessage: sendMessageFetch,
//     messagesUpdate: messageUpdateFetch
// }

// chatNetwork = fetchNetwork;

// function HistoryItem(date, sender, text, sender) {
//     this.date = date.getHours().toString().concat(dateDelimeter, date.getMinutes().toString());
//     this.sender = sender;
//     this.text = text;
//     this.sender = sender;
// }

// function toggleMinimize() {
//     var content = window.document.getElementById(chatContentClass);
//     content.classList.toggle(hiddenClass);
//     minimized = content.classList.contains(hiddenClass);
// }

// function formatItem(item) {
//     var historyItem = document.createElement("div");
//     var messageTime = document.createElement("div");
//     var sender = document.createElement("div");
//     var text = document.createElement("div");

//     historyItem.classList.add(historyItemClass);

//     messageTime.classList.add(messageTimeClass);
//     sender.classList.add(messageSenderClass);
//     text.classList.add(messageTextClass);

//     messageTime.innerText = item.date;
//     sender.innerText = item.sender;
//     text.innerText = item.text;

//     if (window.time) {
//         historyItem.appendChild(messageTime);
//     }
//     historyItem.appendChild(sender);
//     historyItem.appendChild(text);

//     return historyItem;
// }

// function sendAnswer(item) {
//     var history = document.getElementsByClassName(window.messageHistoryClass)[0];
//     var sendDate = new Date();
//     //ToDo:
//     var answerItem = new HistoryItem(
//         sendDate,
//         answer.sender,
//         generateAnswer(item.text)
//     );

//     messages.push(answerItem);
//     chatNetwork.sendMessage(answerItem);

//     history.appendChild(formatItem(answerItem));
// }

// function sendMessage(event) {
//     var history = document.getElementsByClassName(messageHistoryClass)[0];
//     var sendDate = new Date();
//     var item = new HistoryItem(sendDate, username, this.text.value);

//     history.appendChild(formatItem(item));
//     messages.push(item);

//     chatNetwork.sendMessage(item);

//     setTimeout(function () {
//         sendAnswer(item);
//     }, answer.delay);

//     this.text.value = "";

//     event.preventDefault();
// }

// function addStyle(side) {
//     var styles = document.createElement("style");
//     styles.innerHTML =
//         "#chat-panel {\n".concat(
//             "    position: fixed;\n",
//             "    bottom: 0px;\n",
//             "    " + side + ": 10%;\n",
//             "\n",
//             "    background: #3C4896;\n",
//             "    color: white;\n",
//             "    padding: 10px;\n",
//             "    border-top-right-radius: 15px;\n",
//             "    border-top-left-radius: 15px;\n",
//             "\n",
//             "    width: 80%;\n",
//             "    max-width: 400px;\n",
//             "\n",
//             "    font-family: 'Open Sans', sans-serif;\n",
//             "}\n",
//             "#chat-header{\n",
//             "    text-align: right;\n",
//             "}\n",
//             "#chat-message{\n",
//             "    display: table;\n",
//             "    width: 100%;\n",
//             "}\n",
//             "#current-message{\n",
//             "    display: table-cell;\n",
//             "    vertical-align: middle;\n",
//             "    height: 30px;\n",
//             "    padding: 5px;\n",
//             "}\n",
//             "#current-message-area{\n",
//             "    -moz-box-sizing: border-box; /* Для Firefox */\n",
//             "    -webkit-box-sizing: border-box; /* Для Safari и Chrome */\n",
//             "    box-sizing: border-box;\n",
//             "    width: 100%;\n",
//             "    height: 30px;\n",
//             "}\n",
//             "#send-message{\n",
//             "    display: table-cell;\n",
//             "    vertical-align: middle;\n",
//             "    height: 30px;\n",
//             "    padding: 5px;\n",
//             "}\n",
//             "#send-message button{\n",
//             "    height: 30px;\n",
//             "    width: 100%;\n",
//             "}\n",
//             ".message-history{\n",
//             "    display: inline-block;\n",
//             "    background: white;\n",
//             "    color: #3C4896;\n",
//             "    height: 400px;\n",
//             "    width: 100%;\n",
//             "    overflow-y: auto;\n",
//             "    overflow-x: hidden;\n",
//             "    margin-top: 10px;\n",
//             "}\n",
//             ".message-item:after{\n",
//             "   content: \"\"",
//             "    clear: both;\n",
//             "}\n",
//             ".message-item{\n",
//             "    border: #8891CB 2px solid;\n",
//             "    padding-bottom: 5px;\n",
//             "    margin-bottom: 5px;\n",
//             "}\n",
//             ".message-item div{\n",
//             "    padding: 10px;\n",
//             "    float: left;\n",
//             "    margin-right: 5px;\n",
//             "    word-wrap: break-word;\n",
//             "    border-bottom-left-radius: 5px;\n",
//             "    border-bottom-right-radius: 5px;\n",
//             "}\n",
//             ".message-item .message-time{\n",
//             "    background-color: #8891CB;\n",
//             "    color: white;\n",
//             "}\n",
//             ".message-item .message-sender{\n",
//             "    background-color: #BBC1E5;\n",
//             "}\n",
//             ".message-item .message-text{\n",
//             "    float: none;\n",
//             "}",
//             ".hidden{\n",
//             "    display: none;\n",
//             "}",
//             ".drag:hover{",
//             "cursor:move",
//             "}"
//         );

//     document.head.appendChild(styles);
// }

// initChatContent = function () {
//     var messageHistory = document.createElement("div");

//     messageHistory.classList.add(messageHistoryClass);

//     return messageHistory;
// }

// initChatForm = function () {
//     var messageForm = document.createElement("form");

//     messageForm.id = messageFormClass;
//     messageForm.innerHTML =
//         "<div id=\"current-message\">" +
//         "<textarea id=\"current-message-area\" name=\"text\"></textarea>" +
//         "</div>" +
//         "<div id=\"send-message\">" +
//         "<button type=\"submit\">Send</button>" +
//         "</div>";

//     return messageForm;
// }

// initContent = function (chatContent) {
//     var form = initChatForm();
//     form.onsubmit = sendMessage;

//     chatContent.appendChild(initChatContent());
//     chatContent.appendChild(form);
// }

// authUser = function (event) {
//     initContent(document.querySelector("#" + window.chatContentClass));

//     document.querySelector("#" + window.authFormClass).classList.add(window.hiddenClass);

//     username = this.username.value;

//     event.preventDefault();
// }

// initAutorization = function () {
//     var authorization = document.createElement("div");

//     authorization.innerHTML = "<form id='" + authFormClass + "'><input type='text' name='username'><button>Authorize</button></form>";
//     authorization.querySelector("#" + authFormClass).addEventListener("submit", authUser);

//     return authorization;
// }

// moveToPoint = function (element, x, y) {
//     element.style.left = x + "px";
//     element.style.top = y + "px";
//     element.style.right = "auto";
//     element.style.bottom = "auto";
// }

// dragItem = function (event) {
//     var leftMargin = this.getBoundingClientRect().left + window.pageXOffset;
//     var topMargin = this.getBoundingClientRect().top + window.pageYOffset;

//     var shiftX = event.pageX - leftMargin;
//     var shiftY = event.pageY - topMargin;

//     var element = this;

//     var moveEventHandler = function (e) {
//         moveToPoint(element, e.pageX - shiftX, e.pageY - shiftY);
//     };
//     document.addEventListener("mousemove", moveEventHandler);

//     this.onmouseup = function () {
//         document.removeEventListener("mousemove", moveEventHandler);
//     }
// }

// initChatBox = function (header, minimise, cssClass, dragEnable) {
//     var chatBox = window.document.createElement("div");
//     chatBox.id = "chat-panel";
//     chatBox.classList.add(cssClass);
//     chatBox.innerHTML =
//         '<div id="chat-header">' +
//         '<span>' + header + '</span>' +
//         (minimise ? ('<button id="' + window.minimizeButtonClass + '">-</button>\n') : "") +
//         "</div>" +
//         "<div id='" + window.chatContentClass + "'>" +
//         "</div>";

//     if (minimise) {
//         if (window.minimized) {
//             chatBox.querySelector("#" + window.chatContentClass).classList.add(window.hiddenClass);
//         }
//         chatBox.querySelector("#" + window.minimizeButtonClass).onclick = window.toggleMinimize;
//     }

//     if (dragEnable) {
//         chatBox.classList.add(window.dragClass);
//         chatBox.addEventListener("mousedown", window.dragItem);
//     }

//     return chatBox;
// }

// initMessages = function () {
//     window.chatNetwork.messagesUpdate();
// }

// initMinimized = function () {
//     window.minimized = localStorage.getItem(window.minimizedKey) === "true";
// }

// initSide = function () {
//     window.chatSide = localStorage.getItem(window.sideKey);
// }

// initUserId = function () {
//     window.userid = localStorage.getItem(window.userIdKey);
// }

// saveMinimized = function () {
//     localStorage.setItem(window.minimizedKey, window.minimized.toString());
// }

// saveChatSide = function () {
//     localStorage.setItem(window.sideKey, window.chatSide);
// }

// saveUserId = function () {
//     if (window.userid) {
//         localStorage.setItem(window.userIdKey, window.userid);
//     }
// }

// initChat = function (header, userAuth, side, minimise, botName, url, cssClass, dragEnable, showTime, network) {
//     var chat;
//     var authForm;

//     urlAPI = url;
//     time = showTime;

//     if (botName) {
//         answer.sender = botName;
//     }

//     if (localStorage.getItem(sideKey)) {
//         initSide();
//     } else if (side) {
//         chatSide = side;
//     }

//     addStyle(chatSide);

//     if (minimise) {
//         initMinimized();
//         addEventListener("beforeunload", saveMinimized);
//     }

//     if (network) {
//         chatNetwork = network;
//     }

//     chat = initChatBox(header, minimise, cssClass, dragEnable);
//     if (!localStorage.getItem(userIdKey)) {
//         if (userAuth) {
//             authForm = window.initAutorization();
//             chat.querySelector("#" + chatContentClass).appendChild(authForm);
//             authForm.querySelector("#" + authFormClass).addEventListener("submit", chatNetwork.authorize);
//         } else {
//             initContent(chat.querySelector("#" + chatContentClass));
//             username = messageSender;
//         }
//     } else {
//         initUserId();
//         network.getInfo();
//         initContent(chat.querySelector("#" + chatContentClass));
//         initMessages();
//     }

//     setInterval(network.messagesUpdate, messagesUpdateTimeout);

//     document.body.appendChild(chat);
//     printItems(window.messages);

//     addEventListener("beforeunload", window.saveUserId);
//     addEventListener("beforeunload", window.saveChatSide);
// }
