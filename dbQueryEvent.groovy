//select from console
def QUERY = api.db.query("""
  FROM event
  WHERE eventMessages LIKE '%вошел в систему%'
	AND subjectUUID LIKE '%employee%'
  AND eventDate > '07.02.2018'
	AND eventDate < '08.02.2018'""");

def answer = QUERY.list()
def res = ''
answer.subjectUUID.unique().size()

?.each{
  res += it.subjectUUID + ' - ' + it.eventDate + '<br>'
}
res