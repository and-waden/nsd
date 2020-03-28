//Автор: ashelgacheva
//Назначение: скрипт меняющий атрибут заявки (элемент справочника), если все задачи выполнены
/**
* После того как все задачи выполнены (переведены в статус Выполнена) у связанного
заявки атрибут "Статус задач" изменяется на значение "Завершены"
*/
//Версия: 4.0
//Категория: Статусы, условие входа/выхода из статуса
//ПАРАМЕТРЫ-----------------------------------------------------

def TASK_RESOLVED_STATE = 'closed' // Код статуса "Выполнена" задачи
def TASK_SC_ATTR = 'serviceCall' // Код атрибута "Заявка" задачи
def SC_INPROGRESS_STATE = 'closed' // Код элемента справочника "Завершены" атрибута в заявке
def SC_TASKS_ATTR = 'tasks' // Код атрибута "Задачи" заявки

//ОСНОВНОЙ БЛОК-------------------------------------------------

def serviceCall = subject[TASK_SC_ATTR]
if (serviceCall!=null){
def tasks = serviceCall[SC_TASKS_ATTR]



// Первая нерешённая задача
def notResolvedTask = tasks.find {!utils.equal(it, subject) && (it.state != TASK_RESOLVED_STATE)}

// Если нерешённых задач нет, меняем статус заявки
if (notResolvedTask == null)
{
  utils.edit(serviceCall, ['taskState':SC_INPROGRESS_STATE])
}
}