# kahukov2

APPLICATION FLOW (Kuhako)

1. USER CREATION:
      - First is to create a user both client and reseller at the same time.
      - Collector will be create once a Reseller is made. 

2. CONTRACTS:
      - Reseller will create a contract and set it to Specific client.
      - Reseller need to use 'assign Collector to client' function.
      - Client, Collector, Reseller can see the Active contracts that they have using their specific get methods.
         + (localhost:8080/JoinPage/contracts/collector/{collectorId}).
         + (localhost:8080/JoinPage/contracts/reseller/{resellerId}).
         + (localhost:8080/JoinPage/contracts/client/{clientId}).
      - One Contract per Client only.
      
3.a. PAYMENT THROUGH CLIENT (payDues):
      - Client will create payDue and set it to a specific contract.
      - Every paydues amountpayment will subtract from contracts debtRemaining.
      - Once debtRemaining turn to 0, contracts will be deleted and details moved to ContractHistory.
      - Client Can see the transaction (Has Problems, Working on it).

3.b PAYMENT THROUGH COLLECTOR (transaction):
      - Collector will create transaction and set it to a specific contract and client.
      - Every transaction amountpayment will subtract from contracts debtRemaining.
      - Once debtRemaining turn to 0, contracts will be deleted and details moved to ContractHistory.
      - Collectors Can see the transaction.
      
4. CONTRACT HISTORY:
      - Contracts that are completed are automatically moved here.
      - Collector can view Contract History using its specific get method (localhost:8080/JoinPage/contracts/collector/history/{collectorId}).
      
