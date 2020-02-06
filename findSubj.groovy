//find subject from console

def subject = utils.get('serviceCall$197466627')

def atr = []
subject?.mailAccess.each{
  atr += it?.resElement?.title
}
atr
subject?.mailAccess

utils.find('merchandise$oneC', ['call':subject])