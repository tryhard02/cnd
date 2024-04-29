from PIL import Image

def compress_image(input_path, output_path, quality):
    """
    Compresses an image.

    Parameters:
    input_path (str): Path to the input image file.
    output_path (str): Path to save the compressed image file.
    quality (int): Quality of the compressed image (0-100). 
                   Lower value means higher compression.

    Returns:
    None
    """
    image = Image.open(input_path)
    image.save(output_path,format="JPEG", quality=quality)

# Example usage:
input_image_path = "bike.jpg"
output_image_path = "compressed_image.jpg"
compress_image(input_image_path, output_image_path,5)
