//to negotiate HO calls
import groovy.time.TimeCategory
def rests=subject?.restaurant

if ((rests.territorManag)||(rests.subscribers))
{
  CAB = 'cabEmployee'; // код атрибута "Согласующий комитет (сотрудники)".
  SERVICE_CALL = 'sourceR'; // код атрибута "Связанный объект класса Запрос serviceCall".
  WORKS_FQN = 'negotiation$approvalReq'; // идентификатор типа создаваемого объекта
  DESCRIPT = 'description';
  AUTHOR='author';//автор согласования - контрагент заявки
  FILE='files'
  DEADLINE = 'deadLine2';
  //ОСНОВНОЙ БЛОК--------------------------------------------------------
  //откуда копируем

  def attrs = [:];
  attrs[CAB]=[]
  if(rests?.territorManag){
    attrs[CAB]= rests?.territorManag
    utils.edit(subject,['cabEmployee':rests?.territorManag])
  } else{
    attrs[CAB]= rests?.subscribers
    utils.edit(subject,['cabEmployee':rests?.subscribers])
  }
  attrs[SERVICE_CALL]= subject;
  attrs[AUTHOR]=subject?.clientEmployee
  attrs[FILE]=subject?.filesIntegr
  
  def date =  new Date()
  use( TimeCategory ) {
    //date = date + 40.minutes//hours
    date = date + 72.hours
  }
  
  attrs[DEADLINE] = date;

    SERIAL= subject.externalID ?: 'Не указан'

  def TEXT='<strong>'+'Просьба согласовать смету по заявке на обслуживание Холодильного оборудования: '+'</strong>'+'</br>'+'<span style="color: #848484;">'+'Заявка: '+'</span>'+subject?.title+'</br>'+'<span style="color: #848484;">'+'Зона ресторана: '+'</span>'+subject?.restZone?.title+'</br>'+'<span style="color: #848484;">'+'Оборудование: '+'</span>'+subject?.equipment?.title+'</br>'+'<span style="color: #848484;">'+'Тема обращения: '+'</span>'+subject?.scTopic?.title+'</br>'+'<span style="color: #848484;">'+'Описание: '+'</span>'+subject?.descriptionRTF
  
attrs[DESCRIPT]= TEXT
utils.create(WORKS_FQN, attrs);
}