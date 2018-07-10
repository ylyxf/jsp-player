var url = "ws://" + window.location.host + "/websocket";
var ws = new WebSocket(url);
	 
ws.onopen = function(evt) {
	
};

ws.onmessage = function(evt) {
	var urlContentChanged = evt.data;
	var currentUrl = window.location.pathname;
	currentUrl === '/' ? '/index.jsp':currentUrl;
	console.log(urlContentChanged + " is modified");
	if (currentUrl === urlContentChanged) {
		window.location.reload();
	}
};

ws.onclose = function(evt) {
};