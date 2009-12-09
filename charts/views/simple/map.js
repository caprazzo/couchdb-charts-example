function(doc) {
//	emit(doc.date, doc.amount);
	emit(doc.date.split('-'), doc.amount);
}