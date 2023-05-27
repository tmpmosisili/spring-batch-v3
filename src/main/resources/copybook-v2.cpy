      01 TPIPE-Message-Layout.
            05 Y34120D-GTS-PAYLOAD-HDR.
              10 Y34120D-BFUNC-R-HOGAN-TRAN        PIC X(08).
              10 Y34120D-STIMULUS-INDIC            PIC X(01).
              10 Y34120D-STIMULUS                  PIC X(04).
              10 Y34120D-SUSPENSE-KEY.
                 15 Y34120D-PAN                    PIC X(23).
                 15 Y34120D-SETTLE-DATE            PIC 9(07).
                 15 Y34120D-NETW-ID                PIC X(08).
                 15 Y34120D-TRACE-ID               PIC X(12).
              10 Y34120D-BUSINESS-FUNC2            PIC 9(08).
              10 Y34120D-GTS-ERR-INFO.
                 15 Y34120D-GTS-ERR-NUM            PIC 9(05).
                 15 Y34120D-GTS-ERR-DESC           PIC X(40).
            05 API-Message.
      	      10 Y66512D-EVENT-ID                  PIC X(08).
      	  	  10 Y66512D-SCHEMA-VERSION            PIC 9(02).
              10 Y66512D-EVENT-FIELD-NAME          PIC X(40).
      	      10 Y66512D-EVENT-SUBSYSTEM           PIC X(03).
      	      10 Y66512D-EVENT-OWNER               PIC X(20).
      	      10 Y66512D-EVENT-COUNTRY             PIC X(02).
      	      10 Y66512D-EVENT-ENV                 PIC X(05).
      	      10 Y66512D-DATE-TIME.
      	         15 Y66512D-EVENT-DATE             PIC X(10).
      	         15 FILLER                         PIC X(01).
      	         15 Y66512D-EVENT-TIME             PIC X(08).
      	         15 FILLER                         PIC X(01).
      	      10 Y66512D-EVENT-SYSAFF              PIC X(04).
      	      10 Y66512D-EVENT-IMSID               PIC X(04).
      	      10 Y66512D-EVENT-DATA-TYPE           PIC X(10).
      	      10 Y66512D-EVENT-DATA                PIC X(30000).