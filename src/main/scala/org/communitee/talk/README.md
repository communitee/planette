# Communitee > Talk

Human Thought DSL

**Purpose**

Define Human thougts, motivations and emotions in a model.

Currently, Language is our main means of transmission and thus, will serve as the
input medium.

General flow:
Upon transmission(sentence) I decipher the meaning ->
match(pattern based) internal reaction(a meaning too) ->
match(pattern based) course of action and execute it.

Example:
Transmission: How are you?
Meaning: InformationRequest(GeneralStatus) extends Question
Internal Reaction: WillToReact(Answer InformationRequest(GeneralStatus))
Course of action: ReactTo(InformationRequest(GeneralStatus))...
Output: "I'm mostly fine"(result of some Status object with a calculation that scales % to buckets and then translated to a general human label(Good|Great|OK|Fine|Bad|Under the weather|Shitty etc.))

The course of action is, most of the time a non deterministic pipeline -
ReactTo(InformationRequest(GeneralStatus)) -> match reaction -> chain of indicators ->
chain of filters(who asked, how verbose etc.)