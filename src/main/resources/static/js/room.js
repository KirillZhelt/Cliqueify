const liveRoomClient = new LiveRoomClient({
    'username': username,
    'host': 'http://localhost:8080/live',
    'sendTo': '/app/live-room/' + roomId,
    'subscribeTo': '/topic/' + roomId,
});

function enterRoom() {
    liveRoomClient.connect(() => {
        liveRoomClient.sendAction({ type: "ENTER" });
    }, receiveAction);
}

function leaveRoom() {
    liveRoomClient.sendAction({ type: "LEAVE" });
}

function countCurrentElapsedTime(startedToPlay, elapsedTime) {
    const secondsPassed = (new Date() - Date.parse(startedToPlay)) / 1000;
    return elapsedTime + secondsPassed;
}

function receiveAction(action) {
    const actionsArea = document.getElementById('actions-area');
    actionsArea.textContent = actionsArea.textContent + action.username + ' ' + action.action.type + '\n';

    if (action.username !== username) {
        if (action.action.type === 'PLAY') {
            playVideoFrom(countCurrentElapsedTime(action.action.startedToPlayTime, action.action.elapsedTime));
        } else if (action.action.type === 'PAUSE') {
            pauseVideoOn(action.action.elapsedTimeWhenPaused);
        }
    }
}

window.onload = () => {
    enterRoom();
}

window.onbeforeunload = () => {
    leaveRoom();
}

function fillTwoDigits(num) {
    if (num < 10) {
        return '0' + num;
    }

    return num.toString();
}

function formatDate(date) {
    let formatted = `${date.getFullYear()}-`;

    formatted += fillTwoDigits(date.getMonth() + 1);
    formatted += '-';
    formatted += fillTwoDigits(date.getDate());
    formatted += 'T';
    formatted += fillTwoDigits(date.getHours());
    formatted += ':';
    formatted += fillTwoDigits(date.getMinutes());
    formatted += ':';
    formatted += fillTwoDigits(date.getSeconds());

    return formatted;
}

function onPlayButtonClicked() {
    playVideo();
    liveRoomClient.sendAction({
        type: "PLAY",
        elapsedTime: player.getCurrentTime(),
        startedToPlayTime: formatDate(new Date()),
    });
}

function onPauseButtonClicked() {
    pauseVideo();
    liveRoomClient.sendAction({ type: "PAUSE", elapsedTimeWhenPaused: player.getCurrentTime() });
}