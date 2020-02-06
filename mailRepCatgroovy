//get amount calls for category and mail it

import groovy.time.TimeCategory

def secondDate = new Date()
secondDate = secondDate.clearTime()

use( TimeCategory ) {
    secondDate = secondDate - 3.hours
}

def firstDate = secondDate - 1

def newObjCalls = utils.find('serviceCall$incident', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001']).size();
def newResObjCalls = utils.find('serviceCall$incident', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'state':['resolved', 'closed']]).size();
def resObjCalls = utils.find('serviceCall$incident', ['dateDecision':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'state':['resolved', 'closed']]).size();
def overdueObjCalls = utils.find('serviceCall$incident', ['responsibleTeam':'team$101247001', 'state':op.not('resolved', 'closed'), 'overDueOn':op.not(null)]).size();
def resFastObjCalls = utils.find('serviceCall$incident', ['creationDate':op.between(firstDate, secondDate), 'dateDecision':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'state':['resolved', 'closed'], 'overDueOn':null]).size();

def newAllCalls = utils.find('serviceCall', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001']).size();
def newResAllCalls = utils.find('serviceCall', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'state':['resolved', 'closed']]).size();
def resAllCalls = utils.find('serviceCall', ['dateDecision':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'state':['resolved', 'closed']]).size();
def overdueAllCalls = utils.find('serviceCall', ['responsibleTeam':'team$101247001', 'state':op.not('resolved', 'closed'), 'overDueOn':op.not(null)]).size();
def resFastAllCalls = utils.find('serviceCall', ['creationDate':op.between(firstDate, secondDate), 'dateDecision':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'state':['resolved', 'closed'], 'overDueOn':null]).size();

def _1cerp = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cerp']).size() + utils.find('serviceCall$incident', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'scTopic':'catalogs$194401110']).size(); //catalogs$194401110
def _1cwebbuh = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cwebbuh']).size() + utils.find('serviceCall$incident', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'scTopic':'catalogs$95254339']).size(); //catalogs$95254339
def _1cwebzup = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cwebzup']).size();
def _1cwebur = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cwebur']).size();
def _1cbuh = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cbuh']).size();
def _1cdo = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cdo']).size();
def _1czup = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1czup']).size();
def _1ckkm = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1ckkm']).size();
def _1cuat = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cuat']).size();
def _1cupp = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cupp']).size();
def _1cur = utils.find('serviceCall$oneC', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'system1C':'1cur']).size() + utils.find('serviceCall$incident', ['creationDate':op.between(firstDate, secondDate), 'responsibleTeam':'team$101247001', 'scTopic':'catalogs$95254340']).size(); //catalogs$95254340

def msg = """Отчет по 1C за ${firstDate?.toString() + ' - ' + secondDate?.toString()}

<table border="1">
<tr><th>Критерий</th><th>Поступило</th><th>Решено (из поступивших)</th><th>Решено (всего)</th><th>Решено вовремя(за период создания)</th><th>Просрочено</th><th>Уровень сервиса</th></tr>
<tr><td>Кофейни</td><td>${newObjCalls}</td><td>${newResObjCalls}</td><td>${resObjCalls}</td><td>${resFastObjCalls}</td><td>${overdueObjCalls}</td><td>${if (newObjCalls) {resFastObjCalls/newObjCalls} else {'Деленте на ноль'} }</td></tr>
<tr><td>Офис</td><td>${newAllCalls - newObjCalls}</td><td>${newResAllCalls - newResObjCalls}</td><td>${resAllCalls - resObjCalls}</td><td>${resFastAllCalls - resFastObjCalls}</td><td>${overdueAllCalls - overdueObjCalls}</td><td>${if (newAllCalls-newObjCalls) {(resFastAllCalls-resFastObjCalls)/(newAllCalls-newObjCalls)} else {'Деление на ноль'}}</td></tr>
<tr><td>Всего</td><td>${newAllCalls}</td><td>${newResAllCalls}</td><td>${resAllCalls}</td><td>${resFastAllCalls}</td><td>${overdueAllCalls}</td><td>${resFastAllCalls/newAllCalls}</td></tr>
</table>
<br>
<br>
<table border="1">
<tr><th>Инфосистема</th><th>Количество</th></tr>
<tr><td>1C ERP</td><td>${_1cerp}</td></tr>
<tr><td>1C web-Бух</td><td>${_1cwebbuh}</td></tr>
<tr><td>1C web-ЗуП</td><td>${_1cwebzup}</td></tr>
<tr><td>1C web-УР</td><td>${_1cwebur}</td></tr>
<tr><td>1C Бух</td><td>${_1cbuh}</td></tr>
<tr><td>1C ДО</td><td>${_1cdo}</td></tr>
<tr><td>1C ЗуП</td><td>${_1czup}</td></tr>
<tr><td>1C КММ</td><td>${_1ckkm}</td></tr>
<tr><td>1C УАТ</td><td>${_1cuat}</td></tr>
<tr><td>1C УПП</td><td>${_1cupp}</td></tr>
<tr><td>1C УР</td><td>${_1cur}</td></tr>
</table>"""


api.mail.sender.send(['u.user@shoko.ru':null], 'Отчет по 1C за вчера', msg, "text/html")