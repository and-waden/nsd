//
def ous = utils.find('ou$company', ['removed':false, 'concept':['conceptions$2345801', 'conceptions$2233602', 'conceptions$79679301', 'conceptions$2233601']])
def empls = ous.applicants

int START = 0
int COUNT = 50

for(int i = START; i < START + COUNT && i < empls.size(); i++){
  empls[i].each{
    utils.edit(it, ['recipientAgreements':it.recipientAgreements + utils.get('agreement$25454801')])
  }
}