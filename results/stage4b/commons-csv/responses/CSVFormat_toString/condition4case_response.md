{
    "testCases": [
        {
            "group": "toString method",
            "cases": [
                {
                    "input": [],
                    "expected": "Delimiter=<,>",
                    "description": "Test default CSVFormat instance to verify the default delimiter."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<|> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> CommentStart=<#> NullString=<NULL> RecordSeparator=<LF> EmptyLines:ignored SurroundingSpaces:ignored IgnoreHeaderCase:ignored SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test INFORMIX_UNLOAD CSVFormat instance to check all attributes are correctly represented."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<,> Escape=<\\> QuoteChar=<\"> RecordSeparator=<LF> EmptyLines:ignored SurroundingSpaces:ignored IgnoreHeaderCase:ignored SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test EXCEL CSVFormat instance to verify the attributes without comment marker and null string."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test MONGODB_TSV CSVFormat instance to verify tab delimiter and other attributes."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<\t> Escape=<\\> IgnoreEmptyLines:false Quote=null RecordSeparator=<LF> NullString=<NULL> SkipHeaderRecord:false",
                    "description": "Test MYSQL CSVFormat instance to verify attributes including null string."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<,> Escape=<\\> IgnoreEmptyLines:false QuoteChar=<\"> RecordSeparator=<LF> NullString=<NULL> SkipHeaderRecord:false",
                    "description": "Test POSTGRESQL_CSV CSVFormat instance to verify attributes including null string."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<\t> IgnoreSurroundingSpaces:true",
                    "description": "Test TDF CSVFormat instance to ensure surrounding spaces are ignored."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<,> Escape=<\\> QuoteChar=<\"> IgnoreEmptyLines:false IgnoreSurroundingSpaces:false IgnoreHeaderCase:false SkipHeaderRecord:false HeaderComments:null Header:null",
                    "description": "Test RFC4180 CSVFormat instance to verify it does not ignore any attributes."
                }
            ]
        }
    ]
}