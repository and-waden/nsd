def SOURCE=subject.serviceCall

  //добавить задачу на создание учетки
RE = 'responsibleEmployee'; // код атрибута Ответственный сотрудник
RT = 'responsibleTeam'; // код атрибута Ответственный команда
SERVICE_CALL = 'serviceCall'; // код атрибута "В рамках Заявки".
WORKS_FQN = 'task$task'; // идентификатор типа создаваемого объекта
DESCRIPT = 'description';
AUTHOR='author';//автор согласования - контрагент заявки
DL='deadline' //дедлайн задачи=регламентное время заявки
  CAT='workCategory' //категория
  
if(!SOURCE.base1C.isEmpty()){
def attrs = [:];
//attrs[RE]= utils.get('employee$112734710')
attrs[SERVICE_CALL]= SOURCE;
attrs[AUTHOR]=SOURCE.clientEmployee
attrs[RT]=utils.get('team$101247001')
attrs[DL]=SOURCE.deadLineTime+1
attrs[CAT]='access1C'

attrs[DESCRIPT]='В заявке на нового сотрудника №'+SOURCE.number+' необходимо подключение к базам 1С'+'</br>'+'<span style="color: #848484;">'+'ФИО нового сотрудника: '+'</span>'+SOURCE?.newEmplName+'</br>'+'<span style="color: #848484;">'+'Организация (ЮЛ): '+'</span>'+SOURCE?.legalEntity?.title+'</br>'+'<span style="color: #848484;">'+'Департамент/отдел: '+'</span>'+SOURCE?.OU?.title+'</br>'+'<span style="color: #848484;">'+'Должность: '+'</span>'+SOURCE?.position+'</br>'+'<span style="color: #848484;">'+'ФИО руководителя: '+'</span>'+SOURCE?.headName?.title+'</br>'+'<span style="color: #848484;">'+'Роль: '+'</span>'+SOURCE?.roles?.title+'</br>'+'</br>'+'<span style="color: #848484;">'+'Логин: '+'</span>'+SOURCE?.login+'</br>'+'<span style="color: #848484;">'+'Доступ к ресурсам Интернет: '+'</span>'+SOURCE?.internet.title+'</br>'+'<span style="color: #848484;">'+'Подключение к базам 1С: '+'</span>'+SOURCE?.base1C.title+'</br>'+'<span style="color: #848484;">'+'Функции сотрудника: '+'</span>'+SOURCE?.shortDescr+'</br>'+'<span style="color: #848484;">'+'Расположение, адреc: '+'</span>'+SOURCE?.addressEmploye?.title
utils.create(WORKS_FQN, attrs);
}