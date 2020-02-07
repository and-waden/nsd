//do report and mail it
import java.text.SimpleDateFormat
import groovy.time.TimeCategory

def getCallsSLAInfoUL(def team, def startTime, def endDay, def slPriority, def UL){
  
  def callsIT
  def callsPD
  def callsPRNT
  def allCalls
  def resolvedCalls
  def resolvedQuickCalls
  def resolvedNotQuickCalls
  def atWorkQuickCalls
  def atWorkNotQuickCalls
  def returnCalls
  def retValues = [:]
  
  if (slPriority){
    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'legalEntity':UL])
    allCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['allCalls']=allCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'legalEntity':UL])
    resolvedCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['resolvedCalls']=resolvedCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'overDueOn':null, 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'overDueOn':null, 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'overDueOn':null, 'legalEntity':UL])
    resolvedQuickCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['resolvedQuickCalls']=resolvedQuickCalls

    resolvedNotQuickCalls = resolvedCalls - resolvedQuickCalls
    retValues['resolvedNotQuickCalls']=resolvedNotQuickCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'secLineOverdue':'overDue$1522601', 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'secLineOverdue':'overDue$1522601', 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'secLineOverdue':'overDue$1522601', 'legalEntity':UL])
    atWorkQuickCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['atWorkQuickCalls']=atWorkQuickCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'secLineOverdue':'overDue$1522602', 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'secLineOverdue':'overDue$1522602', 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'secLineOverdue':'overDue$1522602', 'legalEntity':UL])
    atWorkNotQuickCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['atWorkNotQuickCalls']=atWorkNotQuickCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'resumeDate': op.not(null), 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'resumeDate': op.not(null), 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'slPriority':slPriority, 'responsibleTeam':team, 'resumeDate': op.not(null), 'legalEntity':UL])
    returnCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['returnCalls']=returnCalls
    
  } else {
    
    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'legalEntity':UL])
    allCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['allCalls']=allCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'legalEntity':UL])
    resolvedCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['resolvedCalls']=resolvedCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'overDueOn':null, 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'overDueOn':null, 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'state':op.in('closed', 'resolved'), 'overDueOn':null, 'legalEntity':UL])
    resolvedQuickCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['resolvedQuickCalls']=resolvedQuickCalls

    resolvedNotQuickCalls = resolvedCalls - resolvedQuickCalls
    retValues['resolvedNotQuickCalls']=resolvedNotQuickCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'secLineOverdue':'overDue$1522601', 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'secLineOverdue':'overDue$1522601', 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'secLineOverdue':'overDue$1522601', 'legalEntity':UL])
    atWorkQuickCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['atWorkQuickCalls']=atWorkQuickCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'secLineOverdue':'overDue$1522602', 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'secLineOverdue':'overDue$1522602', 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'secLineOverdue':'overDue$1522602', 'legalEntity':UL])
    atWorkNotQuickCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['atWorkNotQuickCalls']=atWorkNotQuickCalls

    callsIT = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'resumeDate': op.not(null), 'legalEntity':UL])
    callsPD = utils.find('serviceCall$dataChecking', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'resumeDate': op.not(null), 'legalEntity':UL])
    callsPRNT = utils.find('serviceCall$printCart', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team, 'resumeDate': op.not(null), 'legalEntity':UL])
    returnCalls = callsIT.size() + callsPD.size() + callsPRNT.size()
    retValues['returnCalls']=returnCalls
  }
  return retValues
}

def getReport(def dateStart, def dateStop, def team){
  def pattern = "hh:mm:ss dd.MM.yyyy"
  def timeZone = TimeZone.getTimeZone('Europe/Moscow')
  
  def startTime = Date.parse(pattern, dateStart, timeZone)
  def endDay = Date.parse(pattern, dateStop, timeZone)
  def ULs = utils.find('serviceCall$incident', ['removed': false, 'creationDate': op.between(startTime, endDay), 'responsibleTeam':team])?.legalEntity?.UUID.unique()
  
  def report = ''
  report += team?.title + '<br>'
  report += dateStart + ' - ' + dateStop + '<br>'
  
      def UL = [utils.get('ou$69166817'), utils.get('ou$24321408')]
      def allCalls = getCallsSLAInfoUL(team, startTime, endDay, null, UL)
      def SLA1Calls = getCallsSLAInfoUL(team, startTime, endDay, 'slPriority$91888001', UL)
      def SLA2Calls = getCallsSLAInfoUL(team, startTime, endDay, 'slPriority$91888002', UL)
      def SLA3Calls = getCallsSLAInfoUL(team, startTime, endDay, 'slPriority$91888003', UL)
      def SLA4Calls = getCallsSLAInfoUL(team, startTime, endDay, 'slPriority$91888004', UL)

      def table = """<table border="1">
<tr>
<td colspan="8">${team?.title}</td>
</tr>
<tr>
<td>SLA</td>
<td>Всего</td>
<td>Решено</td>
<td>Решено вовремя</td>
<td>Решено НЕ вовремя</td>
<td>Взято в работу вовремя</td>
<td>Просрочена Реакция</td>
<td>Вернули в работу</td>
</tr>
<tr>
<td>SLA</td>
<td>${allCalls.allCalls}</td>
<td>${allCalls.resolvedCalls}</td>
<td>${allCalls.resolvedQuickCalls}</td>
<td>${allCalls.resolvedNotQuickCalls}</td>
<td>${allCalls.atWorkQuickCalls}</td>
<td>${allCalls.atWorkNotQuickCalls}</td>
<td>${allCalls.returnCalls}</td>
</tr>
<tr>
<td>SLA1</td>
<td>${SLA1Calls.allCalls}</td>
<td>${SLA1Calls.resolvedCalls}</td>
<td>${SLA1Calls.resolvedQuickCalls}</td>
<td>${SLA1Calls.resolvedNotQuickCalls}</td>
<td>${SLA1Calls.atWorkQuickCalls}</td>
<td>${SLA1Calls.atWorkNotQuickCalls}</td>
<td>${SLA1Calls.returnCalls}</td>
</tr>
<tr>
<td>SLA2</td>
<td>${SLA2Calls.allCalls}</td>
<td>${SLA2Calls.resolvedCalls}</td>
<td>${SLA2Calls.resolvedQuickCalls}</td>
<td>${SLA2Calls.resolvedNotQuickCalls}</td>
<td>${SLA2Calls.atWorkQuickCalls}</td>
<td>${SLA2Calls.atWorkNotQuickCalls}</td>
<td>${SLA2Calls.returnCalls}</td>
</tr>
<tr>
<td>SLA3</td>
<td>${SLA3Calls.allCalls}</td>
<td>${SLA3Calls.resolvedCalls}</td>
<td>${SLA3Calls.resolvedQuickCalls}</td>
<td>${SLA3Calls.resolvedNotQuickCalls}</td>
<td>${SLA3Calls.atWorkQuickCalls}</td>
<td>${SLA3Calls.atWorkNotQuickCalls}</td>
<td>${SLA3Calls.returnCalls}</td>
</tr>
<tr>
<td>SLA4</td>
<td>${SLA4Calls.allCalls}</td>
<td>${SLA4Calls.resolvedCalls}</td>
<td>${SLA4Calls.resolvedQuickCalls}</td>
<td>${SLA4Calls.resolvedNotQuickCalls}</td>
<td>${SLA4Calls.atWorkQuickCalls}</td>
<td>${SLA4Calls.atWorkNotQuickCalls}</td>
<td>${SLA4Calls.returnCalls}</td>
</tr>
</table>"""

      def SLA = 97
      def SLA1 = (allCalls.resolvedQuickCalls / allCalls.allCalls) * 100
      def SLA2 = (allCalls.atWorkQuickCalls / allCalls.allCalls) * 100
      def SLA3 = (allCalls.resolvedQuickCalls - allCalls.returnCalls) * 100 / allCalls.allCalls
      def KPI1 = (SLA1/SLA)*(SLA1/SLA)
      def KPI2 = (SLA2/SLA)*(SLA2/SLA)
      def KPI3 = (SLA3/SLA)*(SLA3/SLA)
      def KPIALL = (KPI1*7 + KPI2*2 + KPI3)/10

      table += '<br>'
      table += """<table border="1">
<tr>
<td>SLA</td>
<td>SLA1</td>
<td>SLA2</td>
<td>SLA3</td>
<td>KPI1</td>
<td>KPI2</td>
<td>KPI3</td>
<td>KPIALL</td>
</tr>
<tr>
<td>SLA</td>
<td>Решеные вовремя / Все созданные</td>
<td>Взяты в работу вовремя / Все созданные</td>
<td>(Решеные вовремя - возвращенные) / Все созданные</td>
<td>(SLA1/SLA)^2</td>
<td>(SLA2/SLA)^2</td>
<td>(SLA3/SLA)^2</td>
<td>(KPI1*7+KPI2*2+KPI3*1)/10</td>
</tr>
<tr>
<td>${SLA}</td>
<td>${SLA1}</td>
<td>${SLA2}</td>
<td>${SLA3}</td>
<td>${KPI1}</td>
<td>${KPI2}</td>
<td>${KPI3}</td>
<td>${KPIALL}</td>
</tr>
</table>"""
      
      report += UL?.title + '<br>'
      report += table + '<br><br>'
    return report
}

def dateStart = '00:00:00 01.01.2019'
def dateStop = '23:59:59 31.01.2019'
//def team = utils.get('team$158703703')//А8
def team = utils.get('team$122800201')//ККС КХ и горизонт вместе
//def team = utils.get('team$144083901')//ХэлпЛайн

def report = getReport(dateStart, dateStop, team)

api.mail.sender.send(['u1@shoko.ru':null, 'u2@shoko.ru':null], 'Отчет по заявкам за январь командой ' + team?.title, report, "text/html")