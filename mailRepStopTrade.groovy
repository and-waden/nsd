//mailRepStopTrade
import groovy.time.TimeCategory
def calls = utils.find('serviceCall', ['tradestop':'yn$18823301', 'state':op.not('closed', 'resolved', 'cancelled')])

def tz = TimeZone.getTimeZone("GMT+3")
def msg = ''
if (calls){
msg = '<table border="1" style="font-size: 12px"><tr><th>№ заявки</th><th>Статус</th><th>Дата регистрации</th><th>Дедлайн</th><th>Ресторан</th><th>Тема</th><th>Описание</th><th>Ответственный</th></tr>'
if (calls.size() < 100){
  calls.each{
    if (it?.restaurant?.concept?.UUID != 'conceptions$2278402'){
      msg += "<tr><th style=\"width: 80px;\">${it?.number?.toString()}</th><th>${api.wf.state(it)?.title}</th><th>${it?.creationDate?.format('dd.MM.yyyy HH:mm', tz)}</th><th>${it?.timeAllowanceTimer?.deadLineTime?.format('dd.MM.yyyy HH:mm', tz)}</th><th>${it?.restaurant?.title?.replaceAll('SH ', '')?.replaceAll('KX ', '')?.replaceAll('VS ', '')}</th><th>${it?.shortDescr}</th><th>${api.string.htmlToText(it?.descriptionRTF)}</th><th>${it?.responsible?.title}</th></tr>"//${api.string.htmlToText(it?.descriptionRTF)}
    }
  }
}
msg += '</table>'
} else {
  msg = 'На текущий момент нет открытых заявок по стоп-торговлей!'
}

api.mail.sender.send(['a@shoko.ru':null], 'Отчет по открытым заявкам со стоп торговлей', msg, "text/html")