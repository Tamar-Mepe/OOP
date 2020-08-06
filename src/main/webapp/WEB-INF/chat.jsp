<!doctype html>
<html lang="en">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Droid+Sans:400,700">
<link rel="stylesheet" type="text/css" href="../css/chat.css">


<div id="live-chat">
    <header class="clearfix">
        <a href="#" class="chat-close">x</a>
        <h4>Mehmet Mert</h4>
        <span class="chat-message-counter">3</span>
    </header>
    <div class="chat" style="display: none;">
        <div class="chat-history">
            <div class="chat-message clearfix">
                <img src="ASD">
                <div class="chat-message-content clearfix">
                    <span class="chat-time">13:35</span>
                    <h5>John Doe</h5>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error, explicabo quasi ratione odio
                        dolorum harum.</p>
                </div> <!-- end chat-message-content -->
            </div> <!-- end chat-message -->
            <hr>
            <div class="chat-message clearfix">
                <img src="http://gravatar.com/avatar/2c0ad52fc5943b78d6abe069cc08f320?s=32" alt="" width="32"
                     height="32">
                <div class="chat-message-content clearfix">
                    <span class="chat-time">13:37</span>
                    <h5>Marco Biedermann</h5>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis, nulla accusamus magni vel
                        debitis numquam qui tempora rem voluptatem delectus!</p>
                </div> <!-- end chat-message-content -->
            </div> <!-- end chat-message -->
            <hr>
            <div class="chat-message clearfix">
                <img src="ASD">
                <div class="chat-message-content clearfix">
                    <span class="chat-time">13:38</span>
                    <h5>John Doe</h5>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>
                </div> <!-- end chat-message-content -->
            </div> <!-- end chat-message -->
            <hr>
        </div> <!-- end chat-history -->
        <p class="chat-feedback">Yazıyor..</p>
        <form action="#" method="post">
            <fieldset>
                <input type="text" placeholder="Mesajınızı Yazın" autofocus>
                <input type="hidden">
            </fieldset>
        </form>
    </div> <!-- end chat -->
</div> <!-- end live-chat -->
<script>
    (function () {
        $('#live-chat header').on('click', function () {
            $('.chat').toggle(300, 'swing');
        });
    })();
</script>

</html>