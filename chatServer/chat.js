const server = require("http").createServer();
const io = require("socket.io")(server, {
  transports: ["websocket", "polling"],
});
io.on("connection", (client) => {
  client.on("send", (payload) => {
    io.emit("message", {
      user: payload.user,
      date: new Date().toISOString(),
      text: payload.message,
    });
  });
});
server.listen(3001, () => {
  console.log("Listening on port 3001");
});