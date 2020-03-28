def SC=subject?.sourceR
def MC = api.metainfo.getMetaClass(SC)?.code
if((SC)&&(subject?.cabEmployee?.UUID.contains(subject?.sourceR?.headName?.UUID))&&(MC=='serviceCall$newEmplCall')){
 subject=subject?.sourceR
/*if (subject.mobilePhone==true){     //если моб телефон=да
TASK=''
NEG=''
  
  if((subject?.tariff?.UUID.contains('tariff$45109001'))||(subject?.tariff?.UUID.contains('tariff$19784604'))){
  
  CAB = 'cabEmployee'; // код атрибута "Согласующий комитет (сотрудники)".
SERVICE_CALL = 'sourceR'; // код атрибута "Связанный объект класса Запрос serviceCall".
WORKS_FQN = 'negotiation$approvalReq'; // идентификатор типа создаваемого объекта
DESCRIPT = 'description';
AUTHOR='author';//автор согласования - контрагент заявки

def attrs = [:];
attrs[CAB]= utils.get('employee$11271040')
attrs[SERVICE_CALL]= subject;
//attrs[AUTHOR]=utils.get('employee$2377209')
PH=subject.localPhone?'да':'нет'
MPH=subject.mobilePhone?'да':'нет'
def TEXT='<strong>'+'Просьба согласовать тариф для нового сотрудника: '+'</strong>'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.newEmplName+'</br>'+'<span style="color: #848484;">'+'Организация (ЮЛ): '+'</span>'+subject?.legalEntity?.title+'</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'ФИО руководителя: '+'</span>'+subject?.headName?.title+'</br>'+'<span style="color: #848484;">'+'Стационарный телефон: '+'</span>'+PH+'</br>'+'<span style="color: #848484;">'+'Сотовый телефон: '+'</span>'+MPH+'</br>'+'<span style="color: #848484;">'+'Тариф: '+'</span>'+subject?.tariff?.title+'</br>'+'<span style="color: #848484;">'+'Расположение, адреc: '+'</span>'+subject?.addressEmploye?.title
  attrs[DESCRIPT]= TEXT
utils.create(WORKS_FQN, attrs);
}else{ 
if ((subject?.roles.code == 'executive')||(subject?.roles.code == 'operationHead')||(subject?.roles.code =='stu')||(subject?.roles.code =='regionTerr')||(subject?.roles.code =='head'))  //роль - руководитель
{
def TP=utils.get('tariffPlan$19784902') //ТП руководитель
def tariff=utils.find('tariff',['parent':TP])
tariff=tariff[0]
  if(tariff==subject.tariff){
  	TASK = 1				//поставить признак задачи
  }else{
  	NEG = 1					//поставить признак согласования
  }
}

if ((subject?.roles.code == 'specialist')||(subject?.roles.code == 'manager')) //роль менеджер/специалист
{
	def TP=utils.get('tariffPlan$19784901') //ТП сотрудник
def tariff=utils.find('tariff',['parent':TP])
tariff=tariff[0]
  if(tariff==subject.tariff){
  	TASK = 1				//поставить признак задачи
  }else{
  	NEG = 1					//поставить признак согласования
  }
}
if ((subject?.roles.code == 'tech')||(subject.roles.code == 'cafePers'))	//роль сотрудник
{
def TP=utils.get('tariffPlan$19778201') //ТП кафе
def tariff=utils.find('tariff',['parent':TP])
tariff=tariff[0]
 if(tariff==subject.tariff){
 		TASK = 1				//поставить признак задачи
  }else{
  	NEG = 1					//поставить признак согласования
  }
}

if (TASK==1){				//если стоит признак задачи - создать задачу на моб связь
RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
RT = 'responsibleTeam'; // код атрибута Ответственный команда
SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
DESCRIPT = 'description';
AUTHOR='author';//автор согласования - контрагент заявки
DL='deadline' //дедлайн задачи=регламентное время заявки
CAT='workCategory' //категория
    def attrs = [:];
attrs[RE]= utils.get('employee$2377209')
attrs[SERVICE_CALL]= subject;
attrs[AUTHOR]=subject.clientEmployee
attrs[RT]=utils.get('team$2234403')
attrs[DL]=subject.deadLineTime+1
attrs[CAT]='phoneAdd'
PH=subject.localPhone?'да':'нет'
MPH=subject.mobilePhone?'да':'нет'
attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject.number+' необходимо подключение мобильной связи.'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.newEmplName+'</br>'+'<span style="color: #848484;">'+'Организация (ЮЛ): '+'</span>'+subject?.legalEntity?.title+'</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'ФИО руководителя: '+'</span>'+subject?.headName?.title+'</br>'+'<span style="color: #848484;">'+'Стационарный телефон: '+'</span>'+PH+'</br>'+'<span style="color: #848484;">'+'Сотовый телефон: '+'</span>'+MPH+'</br>'+'<span style="color: #848484;">'+'Тариф: '+'</span>'+subject?.tariff?.title+'</br>'+'<span style="color: #848484;">'+'Расположение, адреc: '+'</span>'+subject?.addressEmploye?.title
utils.create(WORKS_FQN, attrs);
}else{
	if(NEG==1){
	CAB = 'cabEmployee'; // код атрибута "Согласующий комитет (сотрудники)".
SERVICE_CALL = 'sourceR'; // код атрибута "Связанный объект класса Запрос serviceCall".
WORKS_FQN = 'negotiation$approvalReq'; // идентификатор типа создаваемого объекта
DESCRIPT = 'description';
AUTHOR='author';//автор согласования - контрагент заявки

def attrs = [:];
attrs[CAB]= utils.get('employee$2377209')
attrs[SERVICE_CALL]= subject;
attrs[AUTHOR]=utils.get('employee$2377209')
PH=subject.localPhone?'да':'нет'
MPH=subject.mobilePhone?'да':'нет'
def TEXT='<strong>'+'Просьба согласовать тариф для нового сотрудника: '+'</strong>'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.newEmplName+'</br>'+'<span style="color: #848484;">'+'Организация (ЮЛ): '+'</span>'+subject?.legalEntity?.title+'</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'ФИО руководителя: '+'</span>'+subject?.headName?.title+'</br>'+'<span style="color: #848484;">'+'Стационарный телефон: '+'</span>'+PH+'</br>'+'<span style="color: #848484;">'+'Сотовый телефон: '+'</span>'+MPH+'</br>'+'<span style="color: #848484;">'+'Тариф: '+'</span>'+subject?.tariff?.title+'</br>'+'<span style="color: #848484;">'+'Расположение, адреc: '+'</span>'+subject?.addressEmploye?.title
  attrs[DESCRIPT]= TEXT
//attrs[DATE_NEG] = subject.periodNegotiat;
utils.create(WORKS_FQN, attrs);
	}
}
}
}*/

//добавить задачу на создание учетки
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
attrs[CAT]='ADaccountCreate'

attrs[DESCRIPT]='В заявке на нового сотрудника №'+subject.number+' необходимо создание учетной записи'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+subject?.newEmplName+'</br>'+'<span style="color: #848484;">'+'Организация (ЮЛ): '+'</span>'+subject?.legalEntity?.title+'</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+subject?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+subject?.position+'</br>'+'<span style="color: #848484;">'+'ФИО руководителя: '+'</span>'+subject?.headName?.title+'</br>'+'<span style="color: #848484;">'+'Роль: '+'</span>'+subject?.roles?.title+'</br>'+'<span style="color: #848484;">'+'Транслит имени: '+'</span>'+subject?.transliteName+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+subject?.login+'</br>'+'<span style="color: #848484;">'+'Необходимость в ПК: '+'</span>'+subject?.PC.title+'</br>'+'<span style="color: #848484;">'+'Доступ к ресурсам Интернет: '+'</span>'+subject?.internet.title+'</br>'+'<span style="color: #848484;">'+'Подключение почтовых групп: '+'</span>'+subject?.mailGroups.title+'</br>'+'<span style="color: #848484;">'+'Установленный софт: '+'</span>'+subject?.installedSoft+'</br>'+'<span style="color: #848484;">'+'Функции сотрудника: '+'</span>'+subject?.shortDescr+'</br>'+'<span style="color: #848484;">'+'Расположение, адреc: '+'</span>'+subject?.addressEmploye?.title
utils.create(WORKS_FQN, attrs);
}