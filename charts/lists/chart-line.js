function(head, req) {
// ! vendor/porco.js
	log(head);
	log(req);
	start({"headers":{"Content-Type" : "image/svg+xml"}});
	send('<?xml version="1.0"?>');
	send('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="800px" height="400px">\n');
	
	function sigFigs(n, sig) {
	    var mult = Math.pow(10, sig - Math.floor(Math.log(n) / Math.LN10) - 1);
	    return Math.round(n * mult) / mult;
	}
	
	var values = [];
	var max = null;
	var min = null;
	var count = 0;
	while(row = getRow()) {
		if (max==null || row.value > max) { max = row.value };
		if (min==null || row.value < min) { min = row.value };
		values[count] = row;
		log(row);
		count++;
	}
	odiff=max-min;
	max=sigFigs(max*1.05,2)
	min=sigFigs(min*0.95, 2)
	log('MIN: ' + min + ' MAX: ' +max);
	var y_scale = 400.0/(max-min);
	var x_scale = 780.0/count;
	send('<rect x="0" y="0" width="800" height="400" fill="none" stroke="blue"/>\n')
	var ystep = odiff/5;
	for(var i=1; i<6; i++) {
		var yValue = sigFigs(i*ystep, 1); 
		var yline=yValue*y_scale;
		send('<line x1="0" y1="'+yline+'" x2="800" y2="'+yline+'" stroke="green" stroke-width="1" stroke-opacity="0.5"/>\n');
		send('<text style="fill:green; stroke:none" x="10" y="'+(yline-3)+'" >'+yValue+'</text>');
	}
	var x_step = 800/100;
	var j = Math.round(count/x_step);
	send('<polyline style="stroke:black; stroke-width: 1; stroke-linejoin: round; fill: none;" points="');
	for(var i=0; i<count; i++) {
		
		if (i>0) send(',\n')
		send((i*x_scale)+10 + ' ' + (((values[i].value-min)*y_scale)));
	}
	send('"/>');
	for(var i=0; i<count; i++) {	
		if (i%j==0) send('<text style="fill:red;" y="380" x="'+((i*x_scale)+10)+'">'+values[i].key+'</text>');
	}
	log(j);
		

	
	send('</svg>');
}