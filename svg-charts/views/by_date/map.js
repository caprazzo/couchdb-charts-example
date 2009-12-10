// map.js
// key is array representing a date [year][month][day]
// value is each doc amount field (a number)
function(doc) {
	// dates are stored in the doc as 'yyyy-mm-dd'
	emit(doc.date.split('-'), doc.amount);
}