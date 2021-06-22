const http = require('http');

const hostname = '127.0.0.1';
const port = 3000;

const server = http.createServer((req, res) => {
        const now = new Date();
        console.log(`[${now.toLocaleString()}]: ${req.method} method`);
        res.statusCode = 200;
        res.setHeader('Content-Type', 'text/plain');
        if(req.method == 'GET'){
                res.end('GET method');
        } else if (req.method == 'POST'){
                res.end('POST method');
        } else {
                res.statusCode = 405;
                res.end('Bad method');
        }
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});
