{
    "test cases": [
        {
            "group": "toString method",
            "cases": [
                {
                    "input": [],
                    "expected": "Delimiter=<\t> Escape=<\\> QuoteChar=<\"> IgnoreEmptyLines:false IgnoreSurroundingSpaces:false SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test POSTGRESQL_TEXT CSVFormat instance to verify attributes including tab delimiter and other settings."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<|> Escape=<\\> QuoteChar=<\"> QuoteMode=<ALL_NON_NULL> SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test INFORMIX_UNLOAD_CSV CSVFormat instance to verify attributes including all non-null quoting."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<,> Escape=null QuoteChar=<\"> IgnoreEmptyLines:false IgnoreSurroundingSpaces:false SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test ORACLE CSVFormat instance to ensure it handles null escape character correctly."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test MONGODB_CSV CSVFormat instance to verify attributes with tab delimiter and minimal quoting."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<,> Escape=<\\> QuoteChar=<\"> QuoteMode=<ALL_NON_NULL> SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test MYSQL CSVFormat instance to verify attributes including all non-null quoting."
                }
            ]
        }
    ]
}