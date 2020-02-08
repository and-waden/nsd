//transfer calls to resolved from console
def callsNumber = [11826, 11824]

def count = []

callsNumber.each{
  def call = utils.findFirst('serviceCall', ['number':it])
  if (call){
    count+=it
    //call.works.each{work->
      //utils.edit(work, ['state':'closed'])
    //}
    utils.edit(call, ['codeOfClosing':'closureCode$559501', 'state':'resolved'])
  }
}

count