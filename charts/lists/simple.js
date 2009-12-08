function(head, req) {
	start({"headers":{"Content-Type" : "image/svg+xml"}});
	send('<?xml version="1.0"?><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="1200px" height="1200px"><desc>Developer Works Dynamic LineGraph Scaling Example</desc><g transform="translate(50,50) scale(1)"> <!-- Now Draw the main X and Y axis --> <g style="stroke-width:5; stroke:black"> <!-- X Axis --> <path d="M 0 1000 L 1000 1000 Z"/> <!-- Y Axis --> <path d="M 0 0 L 0 1000 Z"/> </g> <g style="fill:none; stroke:#B0B0B0; stroke-width:5; stroke-dasharray:2 4;text-anchor:end; font-size:30"> <path d="M 0 0 L 1000 0 Z"/> <path d="M 0 500 L 1000 500 Z"/> <path d="M 1000 0 L 1000 1000 Z"/> <path d="M 500 0 L 500 1000 Z"/> <text style="fill:black; stroke:none" x="-10" y="0" >1440</text> <text style="fill:black; stroke:none" x="-10" y="500" >720</text> <text style="fill:black; stroke:none" x="510" y="1030" >500</text> <text style="fill:black; stroke:none" x="1010" y="1030" >1000</text> </g> <polyline points="');
	var count = 0;
	while(row = getRow()) {
		send(count + ' ' + row.value/10+ ',\n');
		count++;
	} 
	send('" style="stroke:red; stroke-width: 3; fill : none;"/> </g> </svg>');
}