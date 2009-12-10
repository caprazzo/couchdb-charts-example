function(head, req) {
	start({"headers":{"Content-Type" : "image/svg+xml"}});
	send('<?xml version="1.0"?>');
	send('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"');
	send('<polyline style="stroke:black; stroke-width: 1; stroke-linejoin: round; fill: none;" points="');
	var count = 0;
	while(row = getRow()) {
		if (i>0) send(',\n')
		send(count + ' ' + (row.tot/row.count))
	}
	send('"/>');
	send('</svg>')
}