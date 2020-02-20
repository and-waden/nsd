
<module>
    <code>OKdesk</code>
    <description></description>
    <active>false</active>
    <script checksum="eb1b761dd88b37221ed4007523ff4110dd21f964835c7fc382c678d406779f5c">import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.ContentType.*
import static Constants.*

  
class Constants
{
  static AUTH_KEY = '4a11ebfdec4ed53fa70b29b68f649926de8936b9'
  static ADD_CALL_URL = 'https://sd.a8it.ru/api/v1/issues/?api_token=' + AUTH_KEY
  static ADD_COMMENT_URL = 'https://sd.a8it.ru/api/v1/issues/'
  
  //Тест Шоко
  //static AUTH_KEY = 'd46485745194631d6ad27dde83eedd4817b68570'
  //static ADD_CALL_URL = 'https://shoko-dev.okdesk.ru/api/v1/issues/?api_token=' + AUTH_KEY
}

def createCall(def subject){
  def phone = ' - '
  if (api.metainfo.metaClass(subject?.restaurant).code=='ou$company' &amp;&amp; subject?.restaurant?.phone!=null) {//Если не кофейня проблемы, часть полей нет
    phone =  subject?.restaurant?.phone; 
  }

  def shortDescr = subject?.shortDescr
  if (!shortDescr){
    shortDescr = 'Без темы'
  }
  shortDescr += ' - ' + subject?.number?.toString()
  def description = ''

  if (api.metainfo.metaClass(subject.metaClass).code=='serviceCall$printCart'){//Заказ картриджей
    shortDescr = 'Заказ картриджей - ' + subject?.number?.toString()
    description += 'Услуга: ' + subject?.service?.title + '&lt;br&gt;'
    description += 'Зона ресторана: ' + subject?.restZone?.title + '&lt;br&gt;'
    description += 'Оборудование: ' + subject?.equipment?.title + '&lt;br&gt;'
    description += 'Тема обращения: ' + 'Заказ картриджей' + '&lt;br&gt;'
    description += 'Описание: ' + 'Количество заправленных на момент заявки: ' + subject?.cntCartInOrder?.toString() + '&lt;br&gt;'
    description += 'Количество необходимое: ' + subject?.count?.toString() + '&lt;br&gt;'
    description += 'Количество возвращаемое: ' + subject?.cntCartReturn?.toString() + '&lt;br&gt;'
    description += 'Модель принтера: ' + subject?.printers?.title + '&lt;br&gt;'
    description += 'Артикул картриджа: ' + subject?.artCartridge?.title + '&lt;br&gt;'
    description += 'Модель принтера: ' + subject?.printers?.title + '&lt;br&gt;'
    description += 'Артикул картриджа: ' + subject?.artCartridge?.title + '&lt;br&gt;'
    description += 'Дата заявки: ' + modules.itsm365.getDateTimeInUserTimeZone(subject.registrationDate, null) + '&lt;br&gt;'
    description += 'Крайний срок: ' + modules.itsm365.getDateTimeInUserTimeZone(subject.deadLineTime, null) + '&lt;br&gt;'
    description += 'Приоритет: ' + subject?.slPriority?.code + '&lt;br&gt;'
    description += 'Ресторан: ' + subject?.restaurant?.title + '&lt;br&gt;'
    if (api.metainfo.metaClass(subject?.restaurant).code=='ou$company') {
      description += 'Адрес: ' + subject?.restaurant?.address + '&lt;br&gt;'
    }
    description += 'Телефон: ' + phone + '&lt;br&gt;'
    if (api.metainfo.metaClass(subject?.restaurant).code=='ou$company'){
      description += 'Время работы: ' + subject?.restaurant?.opentime + '&lt;br&gt;'
      description += 'Концепция: ' + subject?.restaurant?.concept?.title + '&lt;br&gt;'
    }
  } else {
    description += 'Услуга: ' + subject?.service?.title + '&lt;br&gt;'
    description += 'Зона ресторана: ' + subject?.restZone?.title + '&lt;br&gt;'
    description += 'Оборудование: ' + subject?.equipment?.title + '&lt;br&gt;'
    description += 'Тема обращения: ' + subject?.scTopic?.title + '&lt;br&gt;'
    description += 'Описание: ' + subject?.descriptionRTF + '&lt;br&gt;'
    description += 'Дата заявки: ' + modules.itsm365.getDateTimeInUserTimeZone(subject.registrationDate, null) + '&lt;br&gt;'
    description += 'Крайний срок: ' + modules.itsm365.getDateTimeInUserTimeZone(subject.deadLineTime, null) + '&lt;br&gt;'
    description += 'Приоритет: ' + subject?.slPriority?.code + '&lt;br&gt;'
    description += 'Ресторан: ' + subject?.restaurant?.title + '&lt;br&gt;'
    if (api.metainfo.metaClass(subject?.restaurant).code=='ou$company'){
      description += 'Адрес: ' + subject?.restaurant?.address + '&lt;br&gt;'
    }
    description += 'Телефон: ' + phone + '&lt;br&gt;'
    if (api.metainfo.metaClass(subject?.restaurant).code=='ou$company'){
      description += 'Время работы: ' + subject?.restaurant?.opentime + '&lt;br&gt;'
      description += 'Концепция: ' + subject?.restaurant?.concept?.title + '&lt;br&gt;'
    }
  }
  //api.web.asLink(api.web.open(subject), 'Ссылка на заявку №' + subject?.number)
  description += 'Заявка в СД Шоколадницы: ' + api.web.asLink(api.web.open(subject), 'Ссылка на заявку №' + subject?.number) + '&lt;br&gt;'
  if (subject?.firstLineEquip){
    description += 'Первичная обработка (оборудование): ' + subject?.firstLineEquip?.title + '&lt;br&gt;'
  }
  if (subject?.firstLineText){
    description += 'Первичная обработка: ' + subject?.firstLineText + '&lt;br&gt;'
  }

  def httpClient = new HTTPBuilder(ADD_CALL_URL)
  httpClient.ignoreSSLIssues()
  def serverResponse

  def custom_parameters = ['external_id':subject?.number?.toString()]
  def data = [issue:['title':shortDescr, 'description':description, 'custom_parameters':custom_parameters]]

  def res = 0

  try {
      httpClient.post(
              requestContentType: ContentType.JSON,
              body: data
      ){ response, reader -&gt;
          if (response.statusLine.statusCode in [200, 201, 204]) {
              serverResponse = reader
            res = 1
          }
      }
  } catch (Exception e) {
      serverResponse = e.message
  }
  return serverResponse.id
}


def update(def jsonAttr){
  if (!jsonAttr){
    return
  }
  //logger.info("OKdesk | update | jsonAttr ${jsonAttr.okdesk_issue_id} ${jsonAttr.external_sd_issue_id} ${jsonAttr.event} ${jsonAttr.comment}")
  def call = utils.findFirst('serviceCall', ['number': jsonAttr.external_sd_issue_id, 'responsibleTeam':'team$158703703'])
  if (call){
    utils.event(call, jsonAttr?.toString())
  }
  
  if (call &amp;&amp; jsonAttr &amp;&amp; jsonAttr.event == 'comment'){
    utils.edit(call, ['@comment':jsonAttr.comment.toString(), '@commentAuthor':'employee$179151303'])
    //utils.edit(call, ['@comment':jsonAttr.toString()])
  }
  def testCall = call//utils.get('serviceCall$183089395')
  if (testCall &amp;&amp; jsonAttr.event == 'status'){
    def attrs = [:]
    //utils.edit(testCall, ['@comment':'Новый статус: ' + jsonAttr.status])
    try{
      //api.mail.sender.send(['a.ivashkevich@shoko.ru':null], 'Интеграция с А8 смена статуса', jsonAttr?.toString(), "text/html")
      if (jsonAttr?.status == 'work'){
        if (jsonAttr.comment){
          attrs+=['@comment':jsonAttr.comment.toString(), '@commentAuthor':'employee$179151303']
        }
        attrs+=['state':'inprogress']
        utils.edit(testCall, attrs)
        //utils.edit(call, ['@comment':'Новый статус из обработчика: ' + jsonAttr.status])
      }
      if (jsonAttr?.status == 'completed' &amp;&amp; jsonAttr.resolution_report &amp;&amp; jsonAttr.resolution_code){
        if (jsonAttr.comment){
          attrs+=['@comment':jsonAttr.comment.toString(), '@commentAuthor':'employee$179151303']
        }
        attrs+=['state':'resolved']
        attrs+=['resultDescr':jsonAttr.resolution_report]
        attrs+=['solvedByTeam':'team$158703703']
        attrs+=['solvedByEmployee':'employee$179151303']
        attrs+=['codeOfClosing':utils.findFirst('closureCode', ['title':jsonAttr.resolution_code, 'parent':'serviceCall'])]
        if (jsonAttr.on_site == 'Да'){
          attrs+=['outWorks':'yn$18823301']
        } else {
          attrs+=['outWorks':'yn$18823302']
        }
        attrs+=['callReason':utils.findFirst('problemReason', ['title':jsonAttr.problem_source?.replace('|', '/')])]
        utils.edit(testCall, attrs)
        //utils.edit(call, ['@comment':'Новый статус из обработчика: ' + jsonAttr.status])
      }
      if (jsonAttr?.status == 'Matching' &amp;&amp; jsonAttr.equipment_type &amp;&amp; jsonAttr.equipment_model &amp;&amp; jsonAttr.equipment_price){
        if (jsonAttr.comment){
          attrs+=['@comment':jsonAttr.comment.toString(), '@commentAuthor':'employee$179151303']
        }
        attrs+=['state':'negotiation']
        attrs+=['model':jsonAttr.equipment_model]
        attrs+=['equipCost':jsonAttr.equipment_price]
        def baseCodes = api.metainfo.getTypes('objectBase').findAll {it.title == jsonAttr.equipment_type}.code
        if (baseCodes){
          attrs+=['equipType':baseCodes[0]]
        } else {
          api.mail.sender.send(['a.ivashkevich@shoko.ru':null], 'Ошибка при интеграции с А8 equipment_type не найден', jsonAttr.equipment_type + '&lt;br&gt;' + jsonAttr?.toString(), "text/html")
        }
        if (jsonAttr.not_in_replacement_fund == true){
          attrs+=['swapPoolOut':'true']
        }
        if (jsonAttr.not_in_replacement_fund == true){
          attrs+=['swapPoolOut':'true']
        }
        if (jsonAttr.equipment_replaced == true){
          attrs+=['temporaryEquip':'true']
        }
        utils.edit(testCall, attrs)
        //utils.edit(call, ['@comment':'Новый статус из обработчика: ' + jsonAttr.status])
      }
    } catch(e){
      api.mail.sender.send(['a.ivashkevich@shoko.ru':null], 'Ошибка при интеграции с А8', e.message + '&lt;br&gt;' + jsonAttr?.toString(), "text/html")
    }
  }
  return 1
}

def sendComment(def obj, def comment){
  if (obj &amp;&amp; obj?.externalID1 &amp;&amp; obj?.responsibleTeam &amp;&amp; obj.responsibleTeam?.UUID == 'team$158703703' &amp;&amp; comment &amp;&amp; comment?.author?.UUID!='employee$179151303'){//А8 и не профиль для интеграции
    def httpClient = new HTTPBuilder(ADD_COMMENT_URL + obj?.externalID1 + '/comments?api_token='+AUTH_KEY)
    httpClient.ignoreSSLIssues()
    def serverResponse

    def data = ['comment':['content':comment.text, 'public':true, 'author_id':'1', 'author_type':'employee']]

    def res = 0

    try {
        httpClient.post(
                requestContentType: ContentType.JSON,
                body: data
        ){ response, reader -&gt;
            if (response.statusLine.statusCode in [200, 201, 204]) {
                serverResponse = reader
              res = 1
            }
        }
    } catch (Exception e) {
        serverResponse = e.message
    }
    return serverResponse.id
  }
}

def sendTextComment(def obj, def text){
  if (obj &amp;&amp; obj?.externalID1 &amp;&amp; obj?.responsibleTeam &amp;&amp; obj.responsibleTeam?.UUID == 'team$158703703' &amp;&amp; text){//А8 и не профиль для интеграции
    def httpClient = new HTTPBuilder(ADD_COMMENT_URL + obj?.externalID1 + '/comments?api_token='+AUTH_KEY)
    httpClient.ignoreSSLIssues()
    def serverResponse

    def data = ['comment':['content':text, 'public':true, 'author_id':'1', 'author_type':'employee']]

    def res = 0

    try {
        httpClient.post(
                requestContentType: ContentType.JSON,
                body: data
        ){ response, reader -&gt;
            if (response.statusLine.statusCode in [200, 201, 204]) {
                serverResponse = reader
              res = 1
            }
        }
    } catch (Exception e) {
        serverResponse = e.message
    }
    return serverResponse.id
  }
}</script>
    <view_by_superusers>true</view_by_superusers>
    <edit_by_superusers>true</edit_by_superusers>
    <author>system</author>
</module>

