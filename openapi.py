from fastapi import FastAPI
import uvicorn

from dostoevsky.tokenization import RegexTokenizer
from dostoevsky.models import FastTextSocialNetworkModel

tokenizer = RegexTokenizer()
model = FastTextSocialNetworkModel(tokenizer=tokenizer)

app = FastAPI()

@app.get("/analyze")
async def analyze_text(text: str):
    print(text)
    return model.predict([text], k=2)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=3210)
