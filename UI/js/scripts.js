function sendMessage() {
    var msg = document.getElementById("messageInput");
    var item = createItem(msg.value);
    document.getElementById("history").appendChild(item);

}
function createItem(messageText) {
    var divItem = document.createElement('div');
    var msg = document.createElement('div');
    var author = document.createElement('div');
    var time = document.createElement('time');
    var txt = document.createElement('div');
    var edit = document.createElement('a');
    var del = document.createElement('a');

    divItem.class = "message";
    msg.class = "mymessage";
    author.class = "author";
    author.innerHTML = "Я";
    txt.class = "text";
    txt.innerHTML = messageText;
    time.innerHTML = new Date().toLocaleString();
    edit.onlick = "editMessage()";
    edit.innerHTML = "Редактировать";
    del.onclick = "deleteMessage()";
    del.onclick = "Удалить";

    divItem.appendChild(msg);
    msg.appendChild(author);
    msg.appendChild(time);
    msg.appendChild(txt);
    msg.appendChild(edit);
    msg.appendChild(del);
    divItem.appendChild(document.createElement('hr'));
    return divItem;
}