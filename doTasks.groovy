//to do included tasks to new call
def createTask(def subject, def deadLine, def description, def responsibleTeam, def responsibleEmployee){
  def attrs = [:];
  attrs['serviceCall']= subject;
  attrs['author']=null
  attrs['deadline']=deadLine
  attrs['description']=description
  attrs['responsibleTeam']=responsibleTeam
  attrs['responsibleEmployee']=responsibleEmployee
  return utils.create('task$task', attrs);
}
def createAccountTask1(def subject){
  RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
  RT = 'responsibleTeam'; // код атрибута Ответственный команда
  SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
  WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
  DESCRIPT = 'description';
  AUTHOR='author';//автор согласования - контрагент заявки
  DL='deadline' //дедлайн задачи=регламентное время заявки
  CAT='workCategory' //категория
  def attrs = [:];
  //attrs[RE]= utils.get('employee$2377209')
  attrs[SERVICE_CALL]= subject;
  attrs[AUTHOR]=subject.clientEmployee
  attrs[RT]=utils.get('team$10862601')
  attrs[DL]=subject.deadLineTime+1
  attrs[CAT]='newAccNewEmpl'
  attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо создание учетной записи'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Транслит имени: '+'</span>'+subject?.surname1 + ' ' + subject.name1 + ' ' + subject.patronymic1+'</br>'+'<span style="color: #848484;">'+'Необходимость в ПК: '+'</span>'+subject?.PC?.title+'</br>'+'Служебный стационарный телефон: '+'</span>'+subject?.localPhone+'</br>'+'Второй монитор: '+'</span>'+subject?.secondDisplay+'</br>'+'Права администратора на локальном ПК: '+'</span>'+subject?.adminRule+'</br>'+'Доступ к удаленному рабочему столу (Терминальная ферма): '+'</span>'+subject?.remDeskAccess+'</br>'+'Удаленный доступ к своему ПК: '+'</span>'+subject?.remoteAccessPC+'</br>'+'VPN: '+'</span>'+subject?.vpn+'</br>'+'Внешний IP: '+'</span>'+subject?.ipAdress+'</br>'+'<span style="color: #848484;">'+'Подключение почтовых групп: '+'</span>'+subject?.mailAccess?.title+'</br>'+'<span style="color: #848484;">'+'Доступ к базам 1С: '+'</span>'+subject?.oneCAccess?.title+'</br>'+'<span style="color: #848484;">'+'Доступ к принтерам: '+'</span>'+subject?.printerAccess?.title+'</br>'+'<span style="color: #848484;">'+'Доступ к сетевым ресурсам: '+'</span>'+subject?.netwAccess?.title+'</br>'+'<span style="color: #848484;">'+'Установленный софт: '+'</span>'+subject?.programs?.title+'</br>'+'<span style="color: #848484;">'+'Доп ПО: '+'</span>'+subject?.installedSoft//+'</br>'+'<span style="color: #848484;">'+'Расположение, адреc: '+'</span>'+subject?.addressEmploye?.title
  utils.create(WORKS_FQN, attrs);
}
def createAccountTask(def subject){
  RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
  RT = 'responsibleTeam'; // код атрибута Ответственный команда
  SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
  WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
  DESCRIPT = 'description';
  AUTHOR='author';//автор согласования - контрагент заявки
  DL='deadline' //дедлайн задачи=регламентное время заявки
  CAT='workCategory' //категория
  def attrs = [:];
  //attrs[RE]= utils.get('employee$2377209')
  attrs[SERVICE_CALL]= subject;
  attrs[AUTHOR]=subject.clientEmployee
  attrs[RT]=utils.get('team$10862601')
  attrs[DL]=subject.deadLineTime+1
  attrs[CAT]='newAccNewEmpl'//newAccNewEmpl
  attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо создание учетной записи и почты'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Необходимость в ПК: '+'</span>'+subject?.PC?.title+'</br>'+'Служебный стационарный телефон: '+'</span>'+subject?.localPhone+'</br>'+'Второй монитор: '+'</span>'
  utils.create(WORKS_FQN, attrs);
}
def createMailTask(def subject){
  def mails = utils.find('merchandise$mail', ['call':subject])
  if (mails){
    RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
    RT = 'responsibleTeam'; // код атрибута Ответственный команда
    SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
    WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
    DESCRIPT = 'description';
    AUTHOR='author';//автор согласования - контрагент заявки
    DL='deadline' //дедлайн задачи=регламентное время заявки
    CAT='workCategory' //категория
    def attrs = [:];
    //attrs[RE]= utils.get('employee$2377209')
    attrs[SERVICE_CALL]= subject;
    attrs[AUTHOR]=subject.clientEmployee
    attrs[RT]=utils.get('team$10862601')
    attrs[DL]=subject.deadLineTime+1
    attrs[CAT]='permissionsSetting'
    attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо добавить доступ к почтовым рассылкам'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Доступ к почтовым рассылкам: '+'</span>'+'</br>' + '<table class = "uni1"><tr><th>Группы</th><th>Доступ</th></tr>'
    mails.each{
    attrs[DESCRIPT] += "<tr><td style=\"width: 80px;\">${it?.resElement?.title}</td><td>${it?.accessRights?.toString()}</td></tr>"
    }
    attrs[DESCRIPT] += """</table>
<style type="text/css">
table.uni1 {
font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
font-size: 14px;
border-collapse: collapse;
text-align: center;
}
table.uni1 > tbody > tr> th, table.uni1 > tbody > tr> th:first-child {
background: #AFCDE7;
color: white;
padding: 10px 20px;

}
table.uni1 > tbody > tr > th, table.uni1 > tbody > tr >td {
border-style: solid;
border-width: 0 1px 1px 0;
border-color: white;
}
table.uni1 > tbody > tr > td{
background: #D8E6F3;
}
table.uni1 > tbody > tr > th:first-child, table.uni1 > tbody > tr > td:first-child {
text-align: center;
}
</style>
"""
    utils.create(WORKS_FQN, attrs);
  }
}
def createOneCTask(def subject){
  def oneCs = utils.find('merchandise$oneC', ['call':subject])
  if (oneCs){
    RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
    RT = 'responsibleTeam'; // код атрибута Ответственный команда
    SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
    WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
    DESCRIPT = 'description';
    AUTHOR='author';//автор согласования - контрагент заявки
    DL='deadline' //дедлайн задачи=регламентное время заявки
    CAT='workCategory' //категория
    def attrs = [:];
    //attrs[RE]= utils.get('employee$2377209')
    attrs[SERVICE_CALL]= subject;
    attrs[AUTHOR]=subject.clientEmployee
    attrs[RT]=utils.get('team$101247001')
    attrs[DL]=subject.deadLineTime+1
    attrs[CAT]='access1C'
    attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо добавить доступ к базам 1С'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Доступ к базам 1С: '+'</span>'+'</br>' + '<table class = "uni1"><tr><th>Группы</th><th>Доступ</th></tr>'
    oneCs.each{
    attrs[DESCRIPT] += "<tr><td style=\"width: 80px;\">${it?.resElement?.title}</td><td>${it?.accessRights?.toString()}</td></tr>"
    }
    attrs[DESCRIPT] += """</table>
<style type="text/css">
table.uni1 {
font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
font-size: 14px;
border-collapse: collapse;
text-align: center;
}
table.uni1 > tbody > tr> th, table.uni1 > tbody > tr> th:first-child {
background: #AFCDE7;
color: white;
padding: 10px 20px;

}
table.uni1 > tbody > tr > th, table.uni1 > tbody > tr >td {
border-style: solid;
border-width: 0 1px 1px 0;
border-color: white;
}
table.uni1 > tbody > tr > td{
background: #D8E6F3;
}
table.uni1 > tbody > tr > th:first-child, table.uni1 > tbody > tr > td:first-child {
text-align: center;
}
</style>
"""
    utils.create(WORKS_FQN, attrs);
  }
}
def createNetwTask(def subject){
  def netws = utils.find('merchandise$netw', ['call':subject])
  if (netws){
    RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
    RT = 'responsibleTeam'; // код атрибута Ответственный команда
    SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
    WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
    DESCRIPT = 'description';
    AUTHOR='author';//автор согласования - контрагент заявки
    DL='deadline' //дедлайн задачи=регламентное время заявки
    CAT='workCategory' //категория
    def attrs = [:];
    //attrs[RE]= utils.get('employee$2377209')
    attrs[SERVICE_CALL]= subject;
    attrs[AUTHOR]=subject.clientEmployee
    attrs[RT]=utils.get('team$10862601')
    attrs[DL]=subject.deadLineTime+1
    attrs[CAT]='permissionsSetting'
    attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо добавить доступ к папкам'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Доступ к сетевым ресурсам: '+'</span>'+'</br>' + '<table class = "uni1"><tr><th>Группы</th><th>Доступ</th></tr>'
    netws.each{
    attrs[DESCRIPT] += "<tr><td style=\"width: 80px;\">${it?.resElement?.title}</td><td>${it?.accessRights?.toString()}</td></tr>"
    }
    attrs[DESCRIPT] += """</table>
<style type="text/css">
table.uni1 {
font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
font-size: 14px;
border-collapse: collapse;
text-align: center;
}
table.uni1 > tbody > tr> th, table.uni1 > tbody > tr> th:first-child {
background: #AFCDE7;
color: white;
padding: 10px 20px;

}
table.uni1 > tbody > tr > th, table.uni1 > tbody > tr >td {
border-style: solid;
border-width: 0 1px 1px 0;
border-color: white;
}
table.uni1 > tbody > tr > td{
background: #D8E6F3;
}
table.uni1 > tbody > tr > th:first-child, table.uni1 > tbody > tr > td:first-child {
text-align: center;
}
</style>
"""
    utils.create(WORKS_FQN, attrs);
  }
}
def createPrinterTask(def subject){
  def printers = utils.find('merchandise$printer', ['call':subject])
  if (printers){
    RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
    RT = 'responsibleTeam'; // код атрибута Ответственный команда
    SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
    WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
    DESCRIPT = 'description';
    AUTHOR='author';//автор согласования - контрагент заявки
    DL='deadline' //дедлайн задачи=регламентное время заявки
    CAT='workCategory' //категория
    def attrs = [:];
    //attrs[RE]= utils.get('employee$2377209')
    attrs[SERVICE_CALL]= subject;
    attrs[AUTHOR]=subject.clientEmployee
    attrs[RT]=utils.get('team$10862601')
    attrs[DL]=subject.deadLineTime+1
    attrs[CAT]='permissionsSetting'
    attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо добавить доступ к принтерам'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Доступ к принтерам: '+'</span>'+'</br>' + '<table class = "uni1"><tr><th>Группы</th><th>Доступ</th></tr>'
    printers.each{
    attrs[DESCRIPT] += "<tr><td style=\"width: 80px;\">${it?.resElement?.title}</td><td>${it?.accessRights?.toString()}</td></tr>"
    }
    attrs[DESCRIPT] += """</table>
<style type="text/css">
table.uni1 {
font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
font-size: 14px;
border-collapse: collapse;
text-align: center;
}
table.uni1 > tbody > tr> th, table.uni1 > tbody > tr> th:first-child {
background: #AFCDE7;
color: white;
padding: 10px 20px;

}
table.uni1 > tbody > tr > th, table.uni1 > tbody > tr >td {
border-style: solid;
border-width: 0 1px 1px 0;
border-color: white;
}
table.uni1 > tbody > tr > td{
background: #D8E6F3;
}
table.uni1 > tbody > tr > th:first-child, table.uni1 > tbody > tr > td:first-child {
text-align: center;
}
</style>
"""
    utils.create(WORKS_FQN, attrs);
  }
}

def createRemoveAccessTask(def subject){
  RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
  RT = 'responsibleTeam'; // код атрибута Ответственный команда
  SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
  WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
  DESCRIPT = 'description';
  AUTHOR='author';//автор согласования - контрагент заявки
  DL='deadline' //дедлайн задачи=регламентное время заявки
  CAT='workCategory' //категория
  def attrs = [:];
  //attrs[RE]= utils.get('employee$2377209')
  attrs[SERVICE_CALL]= subject;
  attrs[AUTHOR]=subject.clientEmployee
  attrs[RT]=utils.get('team$10862601')
  attrs[DL]=subject.deadLineTime+1
  attrs[CAT]='permissionsSetting'
  attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо добавить удаленный доступ'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Доступ к удаленному рабочему столу (Терминальная ферма): '+'</span>'+subject?.remDeskAccess?.toString()+'</br>'+'<span style="color: #848484;">'+'Удаленный доступ к своему ПК: '+'</span>'+subject?.remoteAccessPC?.toString()+'</br>'+'<span style="color: #848484;">'+'VPN: '+'</span>'+subject?.vpn?.toString()+'</br>'+'<span style="color: #848484;">'+'Внешний IP: '+'</span>'+subject?.ipAdress
  utils.create(WORKS_FQN, attrs);
}
def createHardTask(def subject){
  RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
  RT = 'responsibleTeam'; // код атрибута Ответственный команда
  SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
  WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
  DESCRIPT = 'description';
  AUTHOR='author';//автор согласования - контрагент заявки
  DL='deadline' //дедлайн задачи=регламентное время заявки
  CAT='workCategory' //категория
  def attrs = [:];
  //attrs[RE]= utils.get('employee$2377209')
  attrs[SERVICE_CALL]= subject;
  attrs[AUTHOR]=subject.clientEmployee
  attrs[RT]=utils.get('team$10862601')
  attrs[DL]=subject.deadLineTime+1
  attrs[CAT]='makingWorkSpace'
  attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо подготовить и выдать оборудование'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Транслит имени: '+'</span>'+subject?.surname1 + ' ' + subject.name1 + ' ' + subject.patronymic1+'</br>'+'<span style="color: #848484;">'+'Необходимость в ПК: '+'</span>'+subject?.PC?.title+'</br>'+'Служебный стационарный телефон: '+'</span>'+subject?.localPhone+'</br>'+'Второй монитор: '+'</span>'+subject?.secondDisplay+'</br>'+'Права администратора на локальном ПК: '+'</span>'+subject?.adminRule
  utils.create(WORKS_FQN, attrs);
}
def createSoftTask(def subject){
  RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
  RT = 'responsibleTeam'; // код атрибута Ответственный команда
  SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
  WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
  DESCRIPT = 'description';
  AUTHOR='author';//автор согласования - контрагент заявки
  DL='deadline' //дедлайн задачи=регламентное время заявки
  CAT='workCategory' //категория
  def attrs = [:];
  //attrs[RE]= utils.get('employee$2377209')
  attrs[SERVICE_CALL]= subject;
  attrs[AUTHOR]=subject.clientEmployee
  attrs[RT]=utils.get('team$10862601')
  attrs[DL]=subject.deadLineTime+1
  attrs[CAT]='makingWorkSpace'
  attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо установка софта'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Транслит имени: '+'</span>'+subject?.surname1 + ' ' + subject.name1 + ' ' + subject.patronymic1+'</br>'+'<span style="color: #848484;">'+'Cофт: '+'</span>'+subject?.programs?.title+'</br>'+'<span style="color: #848484;">'+'Доп ПО: '+'</span>'+subject?.installedSoft
  utils.create(WORKS_FQN, attrs);
}
def createNaumenTask(def subject){
  RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
  RT = 'responsibleTeam'; // код атрибута Ответственный команда
  SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
  WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
  DESCRIPT = 'description';
  AUTHOR='author';//автор согласования - контрагент заявки
  DL='deadline' //дедлайн задачи=регламентное время заявки
  CAT='workCategory' //категория
  def attrs = [:];
  //attrs[RE]= utils.get('employee$2377209')
  attrs[SERVICE_CALL]= subject;
  attrs[AUTHOR]=subject.clientEmployee
  attrs[RT]=utils.get('team$2419801')
  attrs[DL]=subject.deadLineTime+1
  attrs[CAT]='addSDprofile'
  attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject?.number+' необходимо создание учетной записи Наумен'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.surname + ' ' + subject.name + ' ' + subject.patronymic + '</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'Транслит имени: '+'</span>'+subject?.surname1 + ' ' + subject.name1 + ' ' + subject.patronymic1
  utils.create(WORKS_FQN, attrs);
}
def createCallUZ(def ou){
  def user = utils.get('employee$2234111')//Администратор Ресторана
  def service1 = utils.get('catalogs$185327043')//Учетная запись                                                                
  def service2 = utils.get('catalogs$185450136')//Создание учетной записи
  def theme = 'Просьба создать УЗ для нового ЦФУ ' + ou?.title
  def message = 'Просьба создать УЗ для нового ЦФУ ' + ou?.title + ' и внести информацию в Наумен: https://shoko.itsm365.com/sd/operator/#uuid:'+ou?.UUID
  def res = []
  def systems1C = null
  def baseCategory = null
  def call = utils.create('serviceCall$office', ['clientEmployee':user, 'agreement':'agreement$1492401', 'service':'slmService$185445901', 'equipment':service1, 'scTopic':service2, 'shortDescr':theme, 'descriptionRTF':message, 'goods':res, 'systems1C':systems1C, 'baseCategory':baseCategory, 'restaurant':ou])
}
def createCallMail(def ou){
  def user = utils.get('employee$2234111')//Администратор Ресторана
  def service1 = utils.get('catalogs$185327076')//Почта                                                                
  def service2 = utils.get('catalogs$185327077')//Создание
  def theme = 'Просьба создать Почту для нового ЦФУ ' + ou?.title
  def message = 'Просьба создать Почту для нового ЦФУ ' + ou?.title + ' и внести информацию в Наумен: https://shoko.itsm365.com/sd/operator/#uuid:'+ou?.UUID
  def res = []
  def systems1C = null
  def baseCategory = null
  def call = utils.create('serviceCall$office', ['clientEmployee':user, 'agreement':'agreement$1492401', 'service':'slmService$185445901', 'equipment':service1, 'scTopic':service2, 'shortDescr':theme, 'descriptionRTF':message, 'goods':res, 'systems1C':systems1C, 'baseCategory':baseCategory, 'restaurant':ou])
}








