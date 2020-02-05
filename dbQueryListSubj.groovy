//консольный скрипт подкл к базе и вывод списка сабжектс

def QUERY = api.db.query("""
    FROM event
    WHERE subjectUUID LIKE '%employee%'
    AND eventDate > '01.01.2018'
	AND eventDate < '31.01.2018'""");

def answer = QUERY.list()

def res = ''

answer.size()