function(head, req) {
	start({"headers":{"Content-Type" : "image/svg+xml"}});
	
	// some utility functions
	function line(x1, y1, x2, y2, color) {
		return '<line x1="'+x1+'" y1="'+y1+'" x2="'+x2+'" y2="'+y2+'" style="stroke:'+color+'"/>\n';
	}
	function rect(x, y, width, height, color) {
		return '<rect x="'+x+'" y="'+y+'" width="'+width+'" height="'+height+'" style="fill:none; stroke:'+color+'"/>\n';
	}
	function text(x,y, text) {
		return '<text x="'+x+'" y="'+y+'">'+text+'</text>\n';
	}
	
	// keep only <sig> significant figures, and zero the rest
	function sigFigs(n, sig) {
	    var mult = Math.pow(10, sig - Math.floor(Math.log(n) / Math.LN10) - 1);
	    return Math.round(n * mult) / mult;
	}
	function svg(width, height) {
		return '<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"'+
		' style="fill:black"'+
		' width="'+width+'" height="'+height+'">\n';
	}
	
	var x_size = req.query.width || 750;
	var y_size = req.query.height || 500;

	var y_max = null;
	var y_min = null;
	var values = [];
	var count = 0;
	while(row = getRow()) {		
		var value = Math.ceil(row.value.tot/row.value.count);
		if (y_max==null || value>y_max) { y_max=value; }
		if (y_min==null || value<y_min) { y_min=value; }
		values[count++] = value;
	}
	y_max = sigFigs(y_max*1.05, 2);
	y_min = sigFigs(y_min*0.95, 2);
	var pad = Math.round(y_size/15);
	
	
	send('<?xml version="1.0"?>');
	send(svg(x_size, y_size));
		
	send(rect(1,1, x_size-1, y_size-1, 'red'));
	send(rect(pad,pad, x_size-(2*pad), y_size-(2*pad), 'gray'));
	
	var in_width = x_size-(2*pad);
	var in_height = y_size-(2*pad);
	var in_x_scale = in_width/count;
	var in_y_scale = in_height/(y_max-y_min);

	var y_base = y_size - pad;
	send('<polyline style="stroke:black; stroke-width: 1; fill: none;" points="');
	for(var i=0; i<count; i++) {
		if (i>0) send(',\n');
		var x = pad+Math.round(i*in_x_scale);
		var y = Math.round(y_base - ( (values[i]-y_min) * in_y_scale));
		send( x + ' ' + y);
	}
	send('"/>');
	
	// draw marks on the x axis
	/*
	var x_mark_distance = Math.round(sigFigs(count,1)/10);
	for(var i=1; i<10; i++) {
		var x = pad + (i * x_mark_distance * in_x_scale);
		send(line(x, y_base+(pad/2), x, y_base-(pad/2),'gray'));
		send(text(x+4, y_base+(pad/2), i*x_mark_distance));
	}
	
	// draw marks on the y axis
	var y_mark_distance = Math.round(sigFigs(y_max,1)/10);
	for(var i=0; i<10; i++) {
		var y = y_base-(i*y_mark_distance*in_y_scale);
		send(line(pad/2, y, pad*1.5, y,'gray'));
		send(text(pad/2, y-4, (i*(y_min+y_mark_distance))));
	}
	*/
	
	send('</svg>');

}