{
    "test cases": [
        {
            "group": "toString method",
            "cases": [
                {
                    "input": [],
                    "expected": "Delimiter=<,> SkipHeaderRecord:true",
                    "description": "Test the default CSVFormat with default delimiter and skipHeaderRecord set to true."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<,> Escape=<null> QuoteChar=<null> NullString=<null> RecordSeparator=<\\n> SkipHeaderRecord:true",
                    "description": "Test the POSTGRESQL_CSV CSVFormat with comma delimiter and skipHeaderRecord set to true."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<|> Escape=<null> QuoteChar=<null> SkipHeaderRecord:false",
                    "description": "Test the INFORMIX_UNLOAD CSVFormat with pipe delimiter and default escape character as null."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<\\t> Escape=<null> QuoteChar=<null> SkipHeaderRecord:true",
                    "description": "Test the MYSQL CSVFormat with tab delimiter and skipHeaderRecord set to true."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<,> Escape=<null> QuoteChar=<null> NullString=<null> RecordSeparator=<\\n> SkipHeaderRecord:true",
                    "description": "Test the ORACLE CSVFormat with comma delimiter and skipHeaderRecord set to true."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<\\t> Escape=<null> QuoteChar=<null> SkipHeaderRecord:true",
                    "description": "Test the POSTGRESQL_TEXT CSVFormat with tab delimiter and skipHeaderRecord set to true."
                },
                {
                    "input": [],
                    "expected": "Delimiter=<\\t> Escape=<null> QuoteChar=<null> SkipHeaderRecord:true",
                    "description": "Test the TDF CSVFormat with tab delimiter and skipHeaderRecord set to true."
                }
            ]
        }
    ]
}