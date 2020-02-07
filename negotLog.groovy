//negotiation for logistics
def getRule(def subject, def count = 0){
  utils.findFirst('rules$negotiation', ['services':subject?.service, 'servCallTypes':subject?.metaClass, 'removed':false, 'state':'closed', 'count':count])
}

def createNegotiation(def subject, def rule = null, def count = 0){
  if (!rule){
    rule = getRule(subject, count)
  }
  
  if (rule){
    CAB = 'cabEmployee'; // код атрибута "Согласующий комитет (сотрудники)".
    SERVICE_CALL = 'sourceR'; // код атрибута "Связанный объект класса Запрос serviceCall".
    WORKS_FQN = 'negotiation$approvalReq'; // идентификатор типа создаваемого объекта
    DESCRIPT = 'description';
    AUTHOR='author';//автор согласования - контрагент заявки
    RULE = 'ruleForCreate';//Правило из которого было создано согласование
    def MC = api.metainfo.getMetaClass(subject)?.code//Код метакласса

    def attrs = [:];
    attrs[SERVICE_CALL]= subject;
    attrs[AUTHOR]=subject?.clientEmployee
    attrs[RULE]=rule

    def ERROR = ''
    //В зависимости от типа правила нужно заполнить согласующего
    if (rule?.ruleType?.UUID == 'ruleType$164354202'){//Согласование с ТУ
      if (subject?.restaurant?.territorManag){
        attrs[CAB]=subject?.restaurant?.territorManag
      } else if (subject?.restaurant?.headTerritorMa){
        attrs[CAB]=subject?.restaurant?.headTerritorMa
      }else {
        ERROR = 'ТУ для согласования не указан или находится в архиве, обратитесь в IT'
      }
    }
    if (rule?.ruleType?.UUID == 'ruleType$164354201'){//Согласование с сотрудником
      if (rule?.employees){
        attrs[CAB]=rule?.employees
      } else {
        ERROR = 'Сотрудник для согласования не указан или находится в архиве, обратитесь в IT'
      }
    }
    if (ERROR){
      utils.edit(subject, ['@comment' : ERROR]);
      attrs[CAB]='employee$162461503'
    }

    //В зависимости от типа заявки нужно сформировать особенное описание
    def TEXT = ''
    if((MC == 'serviceCall$transfer')||(MC == 'serviceCall$equipTransport')){
      SERIAL= subject.externalID ?: 'Не указан'
      TEXT='<strong>'+'Просьба согласовать транспортировку: '+'</strong>'+'</br>'+'<span style="color: #848484;">'+'Наименование товара: '+'</span>'+subject?.shortDescr+'</br>'+'<span style="color: #848484;">'+'Инвентарный номер: '+'</span>'+SERIAL+'</br>'+'<span style="color: #848484;">'+'Откуда >>: '+'</span>'+subject?.wherefrom?.title+'</br>'+'<span style="color: #848484;">'+'Куда <<: '+'</span>'+subject?.whereto?.title+'</br>'+'<span style="color: #848484;">'+'Описание: '+'</span>'+subject?.descriptionRTF+'</br>'+'<span style="color: #848484;">'+'Количество: '+'</span>'+subject?.count+'</br>'+'<span style="color: #848484;">'+'Габариты, вес: '+'</span>'+subject?.weith+'</br>'+'<span style="color: #848484;">'+'Дата доставки: '+'</span>'+subject?.deliverDate+'</br>'+'<span style="color: #848484;">'+'Время доставки: '+'</span>'+subject?.delivTime+'</br>'+'<span style="color: #848484;">'+'Плановая дата транспортировки: '+'</span>'+subject?.plannedDate
    } else {
      TEXT='<strong>'+'Просьба согласовать заявку: '+'</strong>'+'</br>'+'<span style="color: #848484;">'+'Описание: '+'</span>'+subject?.descriptionRTF+'</br>'
    }
    attrs[DESCRIPT]= TEXT
    utils.create(WORKS_FQN, attrs);
    if (rule?.newState){
      try{
        utils.edit(subject, ['state':rule?.newState])
      } catch(e){}
    }
  }
}