function(doc) {
  emit([doc._id, 0], doc.amount);
}