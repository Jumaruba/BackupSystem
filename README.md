<p align="center"> 
  <h3 align="center"> Backup system </h3> 
  <p align="center"> A simple distributed backup system developed in java </p> 
</p> 

## How to execute 
Under de ./src directory run the following command to compile the code:
```
$ sh ../scripts/compile.sh
```

After compiling, go to the Build folder located in src folder.
In that folder you can run the following commands:

__Initialize__ the peer running the following command:
```
$ sh ../../scripts/peer.sh <version> <peer_id> <svc_access_point> <mc_addr> <mc_port> <mdb_addr> <mdb_port> <mdr_addr> <mdr_port>
```

__To run__ the TestApp you should run the command:
```
$ sh ../../scripts/test.sh <peer_ap> BACKUP|RESTORE|DELETE|RECLAIM|STATE [<opnd_1> [<optnd_2]] 
```

__To cleanup__ the files run the following code on Build folder
```
$ sh ../../scripts/cleanup.sh <Peer_Id>
``` 

## Introduction 
The aim of this project is to elaborate a distributed system service for backing up files in a LAN.  
All the features requested for this work were implemented:

- Chunk backup
- Chunk restore
- Delete a file
- Manage a local service storage
- Retrieve local service state information

We also implemented the following enhancements:
- __Backup Enhancement__ - Each chunk is not stored more times than its replication degree;
- __Restore Enhancement__ - Using TCP Protocol;
- __Delete Enhancement__ - Upon a delete command, peers that are not working by the time of the request will receive the delete command when connected.  

For more information regarding this project checkout the [report](https://github.com/Jumaruba/BackupSystem/blob/master/doc/SDIS.pdf). 


## Group members

- Diogo Samuel Fernandes (up201806250@fe.up.pt)
- Juliane Marubayashi (201800175@fe.up.pt)
