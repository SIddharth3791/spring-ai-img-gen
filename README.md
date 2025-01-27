# Spring AI Project: Image Generation and Narration using OpenAI APIs

This project demonstrates the integration of **Spring AI** with OpenAI APIs to achieve two main functionalities:

1. **Image Generation**: Leverages OpenAI's `ImageModel` to generate images based on user-provided prompts.
2. **Image Narration**: Utilizes OpenAI's `ChatModel` to describe and narrate the content of an uploaded image.

The project is implemented using Spring Boot and showcases the use of the `ImageModel` and `ChatModel` classes provided by the Spring AI framework to interact with OpenAI APIs efficiently.

---

## Features

- **Generate Images**: Users can input a text prompt to generate an image.
- **Narrate Images**: Users can upload an image, and the application will provide a descriptive narration for it.
- Clean and modular design leveraging Spring Boot and Spring AI for seamless integration.


---

## How It Works

### Image Generation
1. Navigate to the **Generate Image** section of the app.
2. Enter a descriptive prompt (e.g., "A futuristic city at sunset").
3. Submit the prompt, and the app will use OpenAI's `ImageModel` to generate the image.
4. The generated image is displayed to the user.

### Image Narration
1. Navigate to the **Narrate Image** section of the app.
2. Upload an image from your local system.
3. Submit the image, and the app will process it using OpenAI's `ChatModel` to generate a descriptive narration.
4. The narration is displayed as text on the interface.

---
