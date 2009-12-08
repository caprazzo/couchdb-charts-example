function(doc) {
	if(doc.tag=='small-set') 
		emit(doc.date, doc.amount);
}