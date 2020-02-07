//negotiation purchase by chief
CAB = 'cabEmployee'; // код атрибута "Согласующий комитет (команда)".
SERVICE_CALL = 'sourceR'; // код атрибута "Связанный объект класса Запрос serviceCall".
WORKS_FQN = 'negotiation$approvalReq'; // идентификатор типа создаваемого объекта
DESCRIPT = 'description';
AUTHOR='author';//автор согласования - контрагент заявки



//ОСНОВНОЙ БЛОК--------------------------------------------------------
//откуда копируем

def attrs = [:];
attrs[CAB]= subject?.negHead;
attrs[SERVICE_CALL]= subject;
attrs[AUTHOR]=subject.clientEmployee
PH=subject.localPhone?'да':'нет'
MPH=subject.mobilePhone?'да':'нет'

def positions = '<table border="1" style="font-size: 12px"><tr><th>Оборудование</th><th>Количество</th><th>Модель</th><th>Цена за единицу</th></tr>'

subject?.goods?.each{
  positions += "<tr><th style=\"width: 80px;\">${it?.equipCategory?.toString()}</th><th>${it?.amount?.toString()}</th><th>${it?.model?.toString()}</th><th>${it?.price?.toString()}</th></tr>"
}
positions += "</table>"

def TEXT='<strong>'+'Вам, как руководителю, необходимо согласовать заявку на закупку: '+'</strong>'+'</br>'+'<span style="color: #848484;">'+'Описание заявки: '+'</span>'+subject?.descriptionRTF+'</br>'+'<span style="color: #848484;">'+'Оборудование: '+'</br>'+positions
  attrs[DESCRIPT]= TEXT
//attrs[DATE_NEG] = subject.periodNegotiat;
utils.create(WORKS_FQN, attrs);