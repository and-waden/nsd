//nogotiation for IT
import groovy.time.TimeCategory
CAB = 'cabEmployee'; // код атрибута "Согласующий комитет (сотрудник)".
SERVICE_CALL = 'sourceR'; // код атрибута "Связанный объект класса Запрос serviceCall".
WORKS_FQN = 'negotiation$approvalReq'; // идентификатор типа создаваемого объекта
DESCRIPT = 'description';
AUTHOR='author';//автор согласования - контрагент заявки
DEADLINE = 'deadLine2';



//ОСНОВНОЙ БЛОК--------------------------------------------------------
//откуда копируем

def attrs = [:];
attrs[CAB]= 'employee$136722701'// 'team$156860804';
attrs[SERVICE_CALL]= subject;
attrs[AUTHOR]=subject.clientEmployee
PH=subject.localPhone?'да':'нет'
MPH=subject.mobilePhone?'да':'нет'

def date =  new Date()

use( TimeCategory ) {
    //date = date + 40.minutes//hours
  	date = date + 4.hours
}
attrs[DEADLINE] = date;


def TEXT='<strong>'+'Просьба согласовать ИТ заявку: '+'</strong>'+'</br>'+'<span style="color: #848484;">'+'Описание заявки: '+'</span>'+subject?.descriptionRTF+'</br>'+'<span style="color: #848484;">'+'Модель оборудования: '+'</span>'+subject?.model+'</br>'+'<span style="color: #848484;">'+'Стоимость оборудования: '+'</span>'+subject?.equipCost+'</br>'+'<span style="color: #848484;">'+'Установлено подменное оборудование: '+'</span>'+subject?.temporaryEquip
  attrs[DESCRIPT]= TEXT
//attrs[DATE_NEG] = subject.periodNegotiat;
utils.create(WORKS_FQN, attrs);